package com.gwabang.gwabang.member.service;

import com.gwabang.gwabang.department.entity.Department;
import com.gwabang.gwabang.department.repository.DepartmentRepository;
import com.gwabang.gwabang.member.dto.AddMemberRequest;
import com.gwabang.gwabang.department.dto.DepartmentStatsDto;
import com.gwabang.gwabang.member.dto.MemberResponse;
import com.gwabang.gwabang.member.entity.Member;
import com.gwabang.gwabang.member.repository.MemberRepository;
import com.gwabang.gwabang.security.config.jwt.JwtTokenProvider;
import com.gwabang.gwabang.util.NicknameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Transactional
    public Long save(AddMemberRequest dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        String nickname;
        do {
            nickname = NicknameGenerator.generateNickname();
        } while (memberRepository.existsByNickname(nickname)); // 중복 닉네임 방지

        Member member = Member.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .department(department)
                .nickname(nickname) // ← 추가!
                .build();

        Member savedMember = memberRepository.save(member);
        memberRepository.flush();

        return savedMember.getId();
    }


    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 사용자가 없습니다."));
    }

    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public MemberResponse getCurrentUserInfo(String accessToken) {
        Long userId = jwtTokenProvider.getUserIdFromToken(accessToken);
        Member member = memberRepository.findByIdWithDepartment(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return new MemberResponse(member);
    }

    public List<DepartmentStatsDto> getTop3Departments() {
        return memberRepository.findTopDepartmentsByMemberCount(PageRequest.of(0, 3));
    }

    @Transactional
    public void fillEmptyNicknames() {
        List<Member> membersWithoutNickname = memberRepository.findAllByNicknameIsNull();

        for (Member member : membersWithoutNickname) {
            String nickname;
            do {
                nickname = NicknameGenerator.generateNickname();
            } while (memberRepository.existsByNickname(nickname));

            member.setNickname(nickname);
            memberRepository.save(member);
        }
    }
}

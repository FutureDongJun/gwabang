package com.gwabang.gwabang.member.service;

import com.gwabang.gwabang.member.dto.AddMemberRequest;
import com.gwabang.gwabang.member.entity.Member;
import com.gwabang.gwabang.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddMemberRequest dto) {
        Member member = Member.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .departmentId(dto.getDepartmentId())
                .build();

        Member savedMember = memberRepository.save(member);
        memberRepository.flush();

        return savedMember.getId();
    }
}

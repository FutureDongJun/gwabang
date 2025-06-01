package com.gwabang.gwabang.member.service;

import com.gwabang.gwabang.department.entity.Department;
import com.gwabang.gwabang.department.repository.DepartmentRepository;
import com.gwabang.gwabang.member.dto.AddMemberRequest;
import com.gwabang.gwabang.member.entity.Member;
import com.gwabang.gwabang.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Transactional
    public Long save(AddMemberRequest dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        Member member = Member.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .department(department)
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

}

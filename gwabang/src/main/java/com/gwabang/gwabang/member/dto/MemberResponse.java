package com.gwabang.gwabang.member.dto;

import com.gwabang.gwabang.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private Long id;
    private String email;
    private String name;
    private String departmentName;
    private String nickname;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getUsername();
        this.departmentName = member.getDepartment().getName();
        this.nickname = member.getNickname();
    }
}

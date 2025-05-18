package com.gwabang.gwabang.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberRequest {
    private String email;
    private String password;
    private Long departmentId;
}

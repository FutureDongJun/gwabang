package com.gwabang.gwabang.department.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentStatsDto {
    private Long departmentId;
    private String departmentName;
    private Long memberCount;
}

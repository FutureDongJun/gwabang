package com.gwabang.gwabang.departmentgroup.service;

import com.gwabang.gwabang.departmentgroup.entity.DepartmentGroup;
import com.gwabang.gwabang.departmentgroup.repository.DepartmentGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentGroupService {
    private final DepartmentGroupRepository departmentGroupRepository;
    public String getNameByGroupCode(String groupCode) {
        DepartmentGroup departmentGroup = departmentGroupRepository.findByCode(Integer.valueOf(groupCode))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 DepartmentGroup 입니다." + groupCode));
        return departmentGroup.getName();
    }

}

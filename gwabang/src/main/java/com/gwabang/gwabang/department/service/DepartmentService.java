package com.gwabang.gwabang.department.service;

import com.gwabang.gwabang.department.entity.Department;
import com.gwabang.gwabang.department.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Long getDepartmentIdByName(String name) {
        Department department = departmentRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학과입니다: " + name));
        return department.getId();
    }

    public Integer getDepartmentGroupCodeByName(String name) {
        Department department = departmentRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학과입니다: " + name));
        return department.getDepartmentGroup().getCode();
    }
}

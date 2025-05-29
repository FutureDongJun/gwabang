package com.gwabang.gwabang.department.controller;

import com.gwabang.gwabang.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @GetMapping
    public List<String> getDepartmentNames() {
        return departmentRepository.findAllDepartmentNames();
    }
}

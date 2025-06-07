package com.gwabang.gwabang.department.controller;

import com.gwabang.gwabang.department.repository.DepartmentRepository;
import com.gwabang.gwabang.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final DepartmentService departmentService;
    @GetMapping
    public List<String> getDepartmentNames() {
        return departmentRepository.findAllDepartmentNames();
    }

    @GetMapping("/id")
    public ResponseEntity<Long> getDepartmentIdByName(@RequestParam String name) {
        Long departmentId = departmentService.getDepartmentIdByName(name);
        return ResponseEntity.ok(departmentId);
    }

    @GetMapping("/name")
    public ResponseEntity<Integer> getDepartmentGroupCode(@RequestParam String name) {
        Integer groupCode = departmentService.getDepartmentGroupCodeByName(name);
        return ResponseEntity.ok(groupCode);
    }

}

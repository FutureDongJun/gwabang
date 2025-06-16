package com.gwabang.gwabang.departmentgroup.controller;
import com.gwabang.gwabang.department.repository.DepartmentRepository;
import com.gwabang.gwabang.department.service.DepartmentService;
import com.gwabang.gwabang.departmentgroup.repository.DepartmentGroupRepository;
import com.gwabang.gwabang.departmentgroup.service.DepartmentGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departmentGroup")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://44.203.145.236")
public class DepartmentGroupController {

    private final DepartmentGroupService departmentGroupService;
    @GetMapping("/{groupCode}")
    public ResponseEntity<String> getNameByGroupCode(@PathVariable String groupCode) {
        System.out.println("일단받긴함!!!!!!!!!!!!!!!!"+groupCode.getClass().getName());
        String departmentName = departmentGroupService.getNameByGroupCode(groupCode);
        return ResponseEntity.ok(departmentName);
    }
}

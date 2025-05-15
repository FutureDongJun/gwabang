package com.gwabang.gwabang.departmentgroup.entity;

import com.gwabang.gwabang.department.entity.Department;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="departmentgroup")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DepartmentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "departmentGroup")
    private List<Department> departments = new ArrayList<>();


}

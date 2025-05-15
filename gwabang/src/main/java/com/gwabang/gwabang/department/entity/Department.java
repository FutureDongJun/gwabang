package com.gwabang.gwabang.department.entity;

import com.gwabang.gwabang.departmentgroup.entity.DepartmentGroup;
import com.gwabang.gwabang.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="department")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_code")
    private DepartmentGroup departmentGroup;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Member> members = new ArrayList<>();

}

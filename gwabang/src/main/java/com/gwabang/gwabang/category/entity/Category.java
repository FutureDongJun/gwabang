package com.gwabang.gwabang.category.entity;

import com.gwabang.gwabang.article.entity.Article;
import com.gwabang.gwabang.departmentgroup.entity.DepartmentGroup;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_code",nullable = false)
    private DepartmentGroup departmentGroup;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Article> articles = new ArrayList<>();

}
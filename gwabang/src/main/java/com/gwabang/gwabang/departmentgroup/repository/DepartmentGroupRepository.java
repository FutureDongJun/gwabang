package com.gwabang.gwabang.departmentgroup.repository;

import com.gwabang.gwabang.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentGroupRepository extends JpaRepository<Article, Long> {

}

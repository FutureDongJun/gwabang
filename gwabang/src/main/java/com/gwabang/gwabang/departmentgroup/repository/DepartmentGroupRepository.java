package com.gwabang.gwabang.departmentgroup.repository;

import com.gwabang.gwabang.article.entity.Article;
import com.gwabang.gwabang.departmentgroup.entity.DepartmentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentGroupRepository extends JpaRepository<DepartmentGroup, Long> {

    Optional<DepartmentGroup> findByCode(Integer Code);

}

package com.gwabang.gwabang.category.repository;

import com.gwabang.gwabang.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // ✅ DepartmentGroup 안의 groupCode로 Category를 찾는 메서드
    Optional<Category> findByDepartmentGroup_Code(Integer code);
}


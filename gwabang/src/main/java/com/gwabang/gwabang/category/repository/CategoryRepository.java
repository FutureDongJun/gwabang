package com.gwabang.gwabang.category.repository;

import com.gwabang.gwabang.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

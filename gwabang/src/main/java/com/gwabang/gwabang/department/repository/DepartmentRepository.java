package com.gwabang.gwabang.department.repository;

import com.gwabang.gwabang.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d.name FROM Department d")
    List<String> findAllDepartmentNames();

    Optional<Department> findByName(String name);
}



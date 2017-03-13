package com.ryan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ryan.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Page<Department> findByNameContaining(String name, Pageable pageable);

}

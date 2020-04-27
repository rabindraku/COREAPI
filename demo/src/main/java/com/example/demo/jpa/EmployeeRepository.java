package com.example.demo.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository 
        extends JpaRepository<EmployeeEntity, Long> {
	Optional<EmployeeEntity> findById(Long id);
	Optional<EmployeeEntity> findByFirstName(String name);
}
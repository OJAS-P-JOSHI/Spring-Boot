package com.example.springbootjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootjpa.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

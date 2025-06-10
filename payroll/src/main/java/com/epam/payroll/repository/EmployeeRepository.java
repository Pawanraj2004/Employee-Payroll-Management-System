package com.epam.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.payroll.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
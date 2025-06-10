package com.epam.payroll.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.epam.payroll.entity.Employee;
import com.epam.payroll.exception.EmployeeNotFoundException;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id) throws EmployeeNotFoundException;
    Employee updateEmployee(Long id, Employee updatedEmployee) throws EmployeeNotFoundException;
    void deleteEmployee(Long id) throws EmployeeNotFoundException;
    double calculatePayroll();
    double calculateAverageSalaryByDepartment(String departmentName);
    Map<String,List<Employee>>  getEmployeesGroupedByDepartment();
    List<Employee> getTopNHighestPaidEmployees(int n);
    double calculatePayrollByJobTitle(String jobTitle);
    List<Employee> findEmployeesHiredInLastNMonths(int months);
}
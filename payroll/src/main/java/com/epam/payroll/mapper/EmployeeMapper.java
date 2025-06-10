package com.epam.payroll.mapper;

import com.epam.payroll.entity.Employee;
import com.epam.payroll.dto.EmployeeDTO;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setDepartment(employee.getDepartment());
        return employeeDTO;
    }

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId()); 
        employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        return employee;
    }
}
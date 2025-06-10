package com.epam.payroll.controller;

import com.epam.payroll.dto.EmployeeDTO;
import com.epam.payroll.entity.Employee;
import com.epam.payroll.mapper.EmployeeMapper;
import com.epam.payroll.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(EmployeeMapper.toDTO(savedEmployee));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> ResponseEntity.ok(EmployeeMapper.toDTO(employee)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(id, EmployeeMapper.toEntity(employeeDTO));
        return ResponseEntity.ok(EmployeeMapper.toDTO(updatedEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/payroll")
    public ResponseEntity<Double> calculatePayroll() {
        double totalPayroll = employeeService.calculatePayroll();
        return ResponseEntity.ok(totalPayroll);
    }

    @GetMapping("/department/{departmentName}/average-salary")
    public ResponseEntity<Double> calculateAverageSalary(@PathVariable String departmentName) {
        double averageSalary = employeeService.calculateAverageSalaryByDepartment(departmentName);
        return ResponseEntity.ok(averageSalary);
    }

    @GetMapping("/grouped-by-department")
    public ResponseEntity<Map<String, List<Employee>>> getEmployeesGroupedByDepartment() {
        Map<String, List<Employee>> groupedEmployees = employeeService.getEmployeesGroupedByDepartment();
        return ResponseEntity.ok(groupedEmployees);
    }

    @GetMapping("/top-salaries/{n}")
    public ResponseEntity<List<EmployeeDTO>> getTopNHighestPaidEmployees(@PathVariable int n) {
        List<Employee> topEmployees = employeeService.getTopNHighestPaidEmployees(n);
        List<EmployeeDTO> topEmployeeDTOs = topEmployees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(topEmployeeDTOs);
    }

    @GetMapping("/payroll/job-title/{jobTitle}")
    public ResponseEntity<Double> calculatePayrollByJobTitle(@PathVariable String jobTitle) {
        if(jobTitle == null || jobTitle.length()==0)
        throw new IllegalArgumentException("Invalid or Empty Job Title");
        double payroll = employeeService.calculatePayrollByJobTitle(jobTitle);
        return ResponseEntity.ok(payroll);
    }

    @GetMapping("/hired-in-last/{months}")
    public ResponseEntity<List<EmployeeDTO>> findEmployeesHiredInLastNMonths(@PathVariable int months) {
        List<Employee> employees = employeeService.findEmployeesHiredInLastNMonths(months);
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDTOs);
    }
}
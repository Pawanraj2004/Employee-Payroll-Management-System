package com.epam.payroll.service;

import com.epam.payroll.entity.Employee;
import com.epam.payroll.exception.EmployeeNotFoundException;
import com.epam.payroll.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setRole(updatedEmployee.getRole());
            employee.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public double calculatePayroll() {
        // Example base salaries and bonuses by role
        return employeeRepository.findAll().stream()
                .mapToDouble(employee -> {
                    double baseSalary = employee.getSalary();
                    double bonus = 0;

                    // Example: Define bonus based on roles
                    switch (employee.getRole().toLowerCase()) {
                        case "manager":
                            bonus = baseSalary * 0.2; // 20% bonus
                            break;
                        case "developer":
                            bonus = baseSalary * 0.1; // 10% bonus
                            break;
                        case "intern":
                            bonus = baseSalary * 0.05; // 5% bonus
                            break;
                        default:
                            bonus = 0; // No bonus
                            break;
                    }

                    return baseSalary + bonus;
                }).sum(); 
    }

    @Override
    public double calculateAverageSalaryByDepartment(String department){
        List<Employee> list = getAllEmployees();
        return list.stream()
                .filter(x -> x.getDepartment().equalsIgnoreCase(department)) 
                .mapToDouble(Employee::getSalary) 
                .average() 
                .orElse(0.0);
    }

    @Override
    public Map<String,List<Employee>> getEmployeesGroupedByDepartment(){
        List<Employee> list = getAllEmployees();
        return list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }


    @Override
    public List<Employee> getTopNHighestPaidEmployees(int n){
        List<Employee> list = getAllEmployees();
        if(list==null || list.isEmpty())
        throw new IllegalArgumentException("No Employees");
        if(n>list.size())
        throw new IllegalArgumentException("n exceeds the total number of Employees");
        return list.stream().sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary())).limit(n).collect(Collectors.toList());
    }

    @Override
    public double calculatePayrollByJobTitle(String jobTitle){
        List<Employee> list = getAllEmployees();
        return list.stream()
                .filter(x -> x.getDepartment().equalsIgnoreCase(jobTitle)) 
                .mapToDouble(Employee::getSalary) 
                .sum();
    }

    @Override
    public List<Employee> findEmployeesHiredInLastNMonths(int months) {
        LocalDate currentDate = LocalDate.now(); // Get the current date
        LocalDate startDate = currentDate.minusMonths(months); // Calculate the cutoff date

        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getHireDate() != null && employee.getHireDate().isAfter(startDate)) // Filter employees
                .collect(Collectors.toList());
    }
}    
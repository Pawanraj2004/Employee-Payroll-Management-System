package com.epam.payroll;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.epam.payroll.entity.Employee;

@Component
public class CreateEmployee {

    // Setter Injection for 10 Employees
    @Bean
    public Employee emp1() {
        Employee emp = new Employee();
        emp.setName("Pawan");
        emp.setDepartment("ASA");
        emp.setSalary(60000);
        return emp;
    }
    
    @Bean
    public Employee emp2() {
        Employee emp = new Employee();
        emp.setName("John");
        emp.setDepartment("HR");
        emp.setSalary(50000);
        return emp;
    }

    @Bean
    public Employee emp3() {
        Employee emp = new Employee();
        emp.setName("Sarah");
        emp.setDepartment("Finance");
        emp.setSalary(70000);
        return emp;
    }

    @Bean
    public Employee emp4() {
        Employee emp = new Employee();
        emp.setName("Michael");
        emp.setDepartment("IT");
        emp.setSalary(80000);
        return emp;
    }

    @Bean
    public Employee emp5() {
        Employee emp = new Employee();
        emp.setName("Emily");
        emp.setDepartment("Marketing");
        emp.setSalary(55000);
        return emp;
    }

    @Bean
    public Employee emp6() {
        Employee emp = new Employee();
        emp.setName("David");
        emp.setDepartment("Sales");
        emp.setSalary(60000);
        return emp;
    }

    @Bean
    public Employee emp7() {
        Employee emp = new Employee();
        emp.setName("Sophia");
        emp.setDepartment("Legal");
        emp.setSalary(75000);
        return emp;
    }

    @Bean
    public Employee emp8() {
        Employee emp = new Employee();
        emp.setName("Daniel");
        emp.setDepartment("R&D");
        emp.setSalary(85000);
        return emp;
    }

    @Bean
    public Employee emp9() {
        Employee emp = new Employee();
        emp.setName("Liam");
        emp.setDepartment("Support");
        emp.setSalary(45000);
        return emp;
    }

    @Bean
    public Employee emp10() {
        Employee emp = new Employee();
        emp.setName("Mia");
        emp.setDepartment("Operations");
        emp.setSalary(60000);
        return emp;
    }
}
package com.epam.payroll;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.epam.payroll.dto.EmployeeDTO;
import com.epam.payroll.entity.Employee;
import com.epam.payroll.mapper.EmployeeMapper;
import com.epam.payroll.service.EmployeeServiceImpl;

@SpringBootApplication
public class PayrollApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PayrollApplication.class, args);
        
        EmployeeServiceImpl employeeService = context.getBean(EmployeeServiceImpl.class);
        Employee emp1 = (Employee)context.getBean("emp1");
        Employee emp2 = (Employee)context.getBean("emp2");
        Employee emp3 = (Employee)context.getBean("emp3");
        
		employeeService.saveEmployee(emp1);
        employeeService.saveEmployee(emp2);
        employeeService.saveEmployee(emp3);
    }
}

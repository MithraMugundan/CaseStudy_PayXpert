package com.hexaware.px.service;

import com.hexaware.px.entity.Employee;
import com.hexaware.px.exception.EmployeeNotFoundException;

import java.util.List;

public interface IEmployeeService {
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException;
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee) throws EmployeeNotFoundException;
    void deleteEmployee(int employeeId) throws EmployeeNotFoundException;
}

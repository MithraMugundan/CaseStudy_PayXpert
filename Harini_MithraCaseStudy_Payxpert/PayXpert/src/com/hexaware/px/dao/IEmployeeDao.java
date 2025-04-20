package com.hexaware.px.dao;

import com.hexaware.px.entity.Employee;
import com.hexaware.px.exception.EmployeeNotFoundException;

import java.util.List;

public interface IEmployeeDao {
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException;
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee) throws EmployeeNotFoundException;
    void deleteEmployee(int employeeId) throws EmployeeNotFoundException;  // Add this line
}

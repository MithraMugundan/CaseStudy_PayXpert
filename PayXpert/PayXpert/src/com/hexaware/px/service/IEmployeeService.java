
/* @author: Mithra 
 *  Date: 12/04/2025 
 *   Description: Interface for Employee service, */

package com.hexaware.px.service;

import java.util.List;

import com.hexaware.px.entity.Employee;
import com.hexaware.px.exception.EmployeeNotFoundException;

public interface IEmployeeService {
	// Add a new employee to the system
    static int addEmployee(Employee employeeData) {
		// TODO Auto-generated method stub
		return 0;
	}

    // Update an existing employee's details
    int updateEmployee(Employee employeeData);

    // Remove an employee from the system using their ID
    int removeEmployee(int employeeId);

    // Fetch a list of all employees
    static List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

    // Get details of a specific employee by their ID
    Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException;

}

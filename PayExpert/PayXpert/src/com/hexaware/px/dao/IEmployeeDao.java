
/*
/ * @author: Mithra
 * Date: 18/04/2025
 * Description: Employee interface class for defining data access operations related to Employee entities.
 **/

package com.hexaware.px.dao;
import java.util.List;

import com.hexaware.px.entity.Employee;
//import com.hexaware.px.exception.EmployeeNotFoundException;
import com.hexaware.px.exception.EmployeeNotFoundException;

public interface IEmployeeDao {

	// Add a new employee to the system
    int addEmployee(Employee employeeData);

    // Update an existing employee's details
    int updateEmployee(Employee employeeData);

    // Remove an employee from the system using their ID
    int removeEmployee(int employeeId);

    // Fetch a list of all employees
    List<Employee> getAllEmployees();

    // Get details of a specific employee by their ID
    Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException;
}

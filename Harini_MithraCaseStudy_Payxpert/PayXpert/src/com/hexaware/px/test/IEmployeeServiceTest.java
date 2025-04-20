package com.hexaware.px.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.hexaware.px.entity.Employee;
import com.hexaware.px.exception.EmployeeNotFoundException;
import com.hexaware.px.service.IEmployeeService;
import com.hexaware.px.service.EmployeeServiceImpl;
import com.hexaware.px.dao.IEmployeeDao;
import com.hexaware.px.dao.EmployeeDaoImpl;  // Make sure this exists and is correct

public class IEmployeeServiceTest {

    private IEmployeeService employeeService;

    @Before
    public void setUp() {
        // Initialize the EmployeeServiceImpl with the real DAO (this will connect to a real database)
        IEmployeeDao employeeDao = new EmployeeDaoImpl();  // Ensure this is properly connected to your database
        employeeService = new EmployeeServiceImpl(employeeDao);
    }

    // Test Case 5: Verify Error Handling for Invalid Employee Data
    @Test
    public void testVerifyErrorHandlingForInvalidEmployeeData() {
        try {
            // Assuming that employee with ID 99 does not exist in the database
            employeeService.getEmployeeById(99);
            fail("Expected EmployeeNotFoundException to be thrown");
        } catch (EmployeeNotFoundException e) {
            assertEquals("Exception message should be 'Employee not found'", "Employee not found", e.getMessage());
        }
    }
}

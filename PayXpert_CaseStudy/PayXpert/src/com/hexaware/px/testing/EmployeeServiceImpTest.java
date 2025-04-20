package com.hexaware.px.testing;

import com.hexaware.px.dao.IEmployeeDao;
import com.hexaware.px.entity.Employee;
import com.hexaware.px.exception.EmployeeNotFoundException;
import com.hexaware.px.service.EmployeeServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class EmployeeServiceImpTest {

    private IEmployeeDao employeeDao;
    private EmployeeServiceImp employeeService;

   
	@Test
    public void testValidateData_Valid() {
        Employee employee = new Employee(1, "John", "Doe", null, "john.doe@example.com", "1234567890", null, null, null, null, null);
        assertTrue(EmployeeServiceImp.validateData(employee));
    }

    @Test
    public void testValidateData_Invalid() {
        Employee employee = new Employee(1, "Jo", "Do", null, "john.doe.com", "12345", null, null, null, null, null);
        assertFalse(EmployeeServiceImp.validateData(employee));
    }
}
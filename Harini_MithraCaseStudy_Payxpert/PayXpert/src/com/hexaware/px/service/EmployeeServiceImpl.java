package com.hexaware.px.service;

import com.hexaware.px.dao.IEmployeeDao;
import com.hexaware.px.entity.Employee;
import com.hexaware.px.exception.EmployeeNotFoundException;

import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeDao employeeDao;

    public EmployeeServiceImpl(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeServiceImpl() {
		this.employeeDao = null;
		// TODO Auto-generated constructor stub
	}

	@Override
    public Employee addEmployee(Employee employee) {
        // Adding employee using DAO layer
        return employeeDao.addEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException {
        // Fetching employee by ID using DAO
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        // Fetching all employees using DAO
        return employeeDao.getAllEmployees();
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        // Updating employee details using DAO
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) throws EmployeeNotFoundException {
        // Deleting employee by ID using DAO
        employeeDao.deleteEmployee(employeeId);
    }
}

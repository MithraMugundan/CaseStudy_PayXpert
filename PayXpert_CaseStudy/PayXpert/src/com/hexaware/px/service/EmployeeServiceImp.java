package com.hexaware.px.service;

import com.hexaware.px.dao.IEmployeeDao;
import com.hexaware.px.dao.EmployeeDaoImp;
import com.hexaware.px.entity.Employee;
import com.hexaware.px.exception.EmployeeNotFoundException;

import java.util.List;

public class EmployeeServiceImp implements IEmployeeService {

    IEmployeeDao dao = new EmployeeDaoImp();

    public EmployeeServiceImp() {
    }

    public int addEmployee(Employee employeeData) {
        return this.dao.addEmployee(employeeData);
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException {
        return this.dao.getEmployeeById(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return this.dao.getAllEmployees();
    }

    @Override
    public int updateEmployee(Employee employeeData) {
        return this.dao.updateEmployee(employeeData);
    }

    @Override
    public int removeEmployee(int employeeId) {
        return this.dao.removeEmployee(employeeId);
    }

    
    //To Validate data 
    public static boolean validateData(Employee employee) {
        boolean flag = false;
        if (employee.getEmployeeID() > 0 && employee.getFirstName().length() > 2 && employee.getLastName().length() > 2 &&
            employee.getEmail().contains("@") && employee.getPhoneNumber().length() > 9) {
            flag = true;
        }

        return flag;
    }
}




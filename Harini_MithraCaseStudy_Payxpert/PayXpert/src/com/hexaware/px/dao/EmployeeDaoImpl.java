package com.hexaware.px.dao;

import com.hexaware.px.entity.Employee;
import com.hexaware.px.exception.EmployeeNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {

    private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO Employee (firstName, lastName, dateOfBirth, gender, email, phoneNumber, address, position, joiningDate, terminationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_EMPLOYEE_BY_ID_QUERY = "SELECT * FROM Employee WHERE employeeId = ?";
    private static final String SELECT_ALL_EMPLOYEES_QUERY = "SELECT * FROM Employee";
    private static final String UPDATE_EMPLOYEE_QUERY = "UPDATE Employee SET firstName = ?, lastName = ?, dateOfBirth = ?, gender = ?, email = ?, phoneNumber = ?, address = ?, position = ?, joiningDate = ?, terminationDate = ? WHERE employeeId = ?";
    private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM Employee WHERE employeeId = ?";  // Query to delete employee

    private Connection connection;

    public EmployeeDaoImpl() {
        try {
            connection = DBUtil.getDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception appropriately
        }
    }

    @Override
    public Employee addEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setString(6, employee.getPhoneNumber());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setString(8, employee.getPosition());
            preparedStatement.setDate(9, Date.valueOf(employee.getJoiningDate()));
            preparedStatement.setDate(10, employee.getTerminationDate() != null ? Date.valueOf(employee.getTerminationDate()) : null);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    employee.setEmployeeId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception appropriately
        }
        return employee;
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID_QUERY)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Employee(
                        resultSet.getInt("employeeId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dateOfBirth").toLocalDate(),
                        resultSet.getString("gender"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address"),
                        resultSet.getString("position"),
                        resultSet.getDate("joiningDate").toLocalDate(),
                        resultSet.getDate("terminationDate") != null ? resultSet.getDate("terminationDate").toLocalDate() : null
                );
            } else {
                throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_EMPLOYEES_QUERY);
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("employeeId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dateOfBirth").toLocalDate(),
                        resultSet.getString("gender"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address"),
                        resultSet.getString("position"),
                        resultSet.getDate("joiningDate").toLocalDate(),
                        resultSet.getDate("terminationDate") != null ? resultSet.getDate("terminationDate").toLocalDate() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle exception
        }
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_QUERY)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setString(6, employee.getPhoneNumber());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setString(8, employee.getPosition());
            preparedStatement.setDate(9, Date.valueOf(employee.getJoiningDate()));
            preparedStatement.setDate(10, employee.getTerminationDate() != null ? Date.valueOf(employee.getTerminationDate()) : null);
            preparedStatement.setInt(11, employee.getEmployeeId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new EmployeeNotFoundException("Employee with ID " + employee.getEmployeeId() + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle exception
        }
    }

    @Override
    public void deleteEmployee(int employeeId) throws EmployeeNotFoundException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_QUERY)) {
            preparedStatement.setInt(1, employeeId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle exception
        }
    }
}

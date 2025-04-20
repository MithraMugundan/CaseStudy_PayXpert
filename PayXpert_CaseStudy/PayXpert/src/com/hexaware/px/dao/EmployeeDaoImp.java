package com.hexaware.px.dao;

import com.hexaware.px.entity.Employee;
import com.hexaware.px.exception.EmployeeNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Custom exception class for DAO errors
class DaoException extends Exception {
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class EmployeeDaoImp implements IEmployeeDao {

    public EmployeeDaoImp() {}

    /**
     * Adds a new employee record into the database.
     * @param employeeData Employee object containing employee details
     * @return number of rows affected (1 if successful, 0 if failure)
     */
    public int addEmployee(Employee employeeData) {
        int count = 0;

        try {
            Connection conn = DBUtil.getDBConnection();
            if (conn == null) {
                throw new SQLException("Failed to establish a database connection.");
            }

            String insert = "INSERT INTO employee (firstName, lastName, dateOfBirth, gender, email, phoneNumber, address, position, joiningDate, terminationDate) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, employeeData.getFirstName());
            pstmt.setString(2, employeeData.getLastName());
            pstmt.setDate(3, new java.sql.Date(employeeData.getDateOfBirth().getTime()));
            pstmt.setString(4, employeeData.getGender());
            pstmt.setString(5, employeeData.getEmail());
            pstmt.setString(6, employeeData.getPhoneNumber());
            pstmt.setString(7, employeeData.getAddress());
            pstmt.setString(8, employeeData.getPosition());
            pstmt.setDate(9, new java.sql.Date(employeeData.getJoiningDate().getTime()));
            pstmt.setDate(10, employeeData.getTerminationDate() != null ? new java.sql.Date(employeeData.getTerminationDate().getTime()) : null);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * Retrieves all employee records from the database.
     * @return List of Employee objects
     */
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();  // Initialize list to avoid null issues

        try {
            Connection conn = DBUtil.getDBConnection();
            if (conn == null) {
                throw new SQLException("Failed to establish a database connection.");
            }

            String select = "SELECT * FROM employee";
            PreparedStatement pstmt = conn.prepareStatement(select);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("employeeID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getDate("dateOfBirth"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("address"),
                    rs.getString("position"),
                    rs.getDate("joiningDate"),
                    rs.getDate("terminationDate")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;  // Return the list, which will be empty if no employees found
    }

    /**
     * Retrieves a single employee by their ID.
     * @param employeeId Employee ID
     * @return Employee object
     * @throws EmployeeNotFoundException if employee with the given ID is not found
     */
    public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException {
        Employee emp = null;

        try {
            Connection conn = DBUtil.getDBConnection();
            if (conn == null) {
                throw new SQLException("Failed to establish a database connection.");
            }

            String select = "SELECT * FROM employee WHERE employeeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new EmployeeNotFoundException();
            }

            emp = new Employee(
                rs.getInt("employeeID"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("dateOfBirth"),
                rs.getString("gender"),
                rs.getString("email"),
                rs.getString("phoneNumber"),
                rs.getString("address"),
                rs.getString("position"),
                rs.getDate("joiningDate"),
                rs.getDate("terminationDate")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    /**
     * Updates an existing employee record in the database.
     * @param employeeData Employee object containing the updated employee details
     * @return number of rows affected (1 if successful, 0 if failure)
     */
    public int updateEmployee(Employee employeeData) {
        int count = 0;

        try {
            Connection conn = DBUtil.getDBConnection();
            if (conn == null) {
                throw new SQLException("Failed to establish a database connection.");
            }

            String update = "UPDATE employee SET firstName = ?, lastName = ?, dateOfBirth = ?, gender = ?, email = ?, " +
                            "phoneNumber = ?, address = ?, position = ?, joiningDate = ?, terminationDate = ? " +
                            "WHERE employeeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1, employeeData.getFirstName());
            pstmt.setString(2, employeeData.getLastName());
            pstmt.setDate(3, new java.sql.Date(employeeData.getDateOfBirth().getTime()));
            pstmt.setString(4, employeeData.getGender());
            pstmt.setString(5, employeeData.getEmail());
            pstmt.setString(6, employeeData.getPhoneNumber());
            pstmt.setString(7, employeeData.getAddress());
            pstmt.setString(8, employeeData.getPosition());
            pstmt.setDate(9, new java.sql.Date(employeeData.getJoiningDate().getTime()));
            pstmt.setDate(10, employeeData.getTerminationDate() != null ? new java.sql.Date(employeeData.getTerminationDate().getTime()) : null);
            pstmt.setInt(11, employeeData.getEmployeeID());  // Update based on employeeID
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * Removes an employee record from the database using the employee ID.
     * @param employeeId Employee ID
     * @return number of rows affected (1 if successful, 0 if failure)
     */
    public int removeEmployee(int employeeId) {
        int count = 0;

        try {
            Connection conn = DBUtil.getDBConnection();
            if (conn == null) {
                throw new SQLException("Failed to establish a database connection.");
            }

            String delete = "DELETE FROM employee WHERE employeeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(delete);
            pstmt.setInt(1, employeeId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}

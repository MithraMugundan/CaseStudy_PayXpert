/*
/ * @author: Mithra
 * Date: 18/04/2025
 * Description: This class implements the IEmployeeDao interface to perform
 *              database operations related to Employee such as adding, updating,
 *              deleting, and fetching employee records.
 */

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

public class EmployeeDaoImp implements IEmployeeDao {

    public EmployeeDaoImp() {
    }

    /**
     * Adds a new employee record into the database.
     * @param employeeData Employee object containing employee details
     * @return number of rows affected (1 if successful, 0 if failure)
     */
    public int addEmployee(Employee employeeData) {
        int count = 0;

        try {
            Connection conn = DBUtil.getDBConnection();
            String insert = "insert into employee (employeeID, firstName, lastName, dateOfBirth, gender, email, phoneNumber, address, position, joiningDate, terminationDate) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1, employeeData.getEmployeeID());
            pstmt.setString(2, employeeData.getFirstName());
            pstmt.setString(3, employeeData.getLastName());
            pstmt.setDate(4, new java.sql.Date(employeeData.getDateOfBirth().getTime()));
            pstmt.setString(5, employeeData.getGender());
            pstmt.setString(6, employeeData.getEmail());
            pstmt.setString(7, employeeData.getPhoneNumber());
            pstmt.setString(8, employeeData.getAddress());
            pstmt.setString(9, employeeData.getPosition());
            pstmt.setDate(10, new java.sql.Date(employeeData.getJoiningDate().getTime()));
            pstmt.setDate(11, employeeData.getTerminationDate() != null ? new java.sql.Date(employeeData.getTerminationDate().getTime()) : null);
            count = pstmt.executeUpdate();
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return count;
    }

    /**
     * Retrieves all employee records from the database.
     * @return List of Employee objects
     */
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();

        try {
            Connection conn = DBUtil.getDBConnection();
            String select = "select * from employee";
            PreparedStatement pstmt = conn.prepareStatement(select);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int employeeID = rs.getInt("employeeID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                String position = rs.getString("position");
                Date joiningDate = rs.getDate("joiningDate");
                Date terminationDate = rs.getDate("terminationDate");

                Employee emp = new Employee(employeeID, firstName, lastName, dateOfBirth, gender, email, phoneNumber, address, position, joiningDate, terminationDate);
                list.add(emp);
            }
        } catch (SQLException var12) {
            var12.printStackTrace();
        }

        return list;
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
            String select = "select * from employee where employeeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                throw new EmployeeNotFoundException();
            }

            int employeeID = rs.getInt("employeeID");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            Date dateOfBirth = rs.getDate("dateOfBirth");
            String gender = rs.getString("gender");
            String email = rs.getString("email");
            String phoneNumber = rs.getString("phoneNumber");
            String address = rs.getString("address");
            String position = rs.getString("position");
            Date joiningDate = rs.getDate("joiningDate");
            Date terminationDate = rs.getDate("terminationDate");

            emp = new Employee(employeeID, firstName, lastName, dateOfBirth, gender, email, phoneNumber, address, position, joiningDate, terminationDate);
        } catch (SQLException var12) {
            var12.printStackTrace();
        }

        return emp;
    }

    /**
     * Updates an existing employee record in the database.
     * Currently not implemented, will update employee details.
     * @param employeeData Employee object with updated information
     * @return number of rows affected (1 if successful, 0 if failure)
     */
    public int updateEmployee(Employee employeeData) {
        return 0;
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
            String delete = "delete from employee where employeeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(delete);
            pstmt.setInt(1, employeeId);
            count = pstmt.executeUpdate();
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return count;
    }
}

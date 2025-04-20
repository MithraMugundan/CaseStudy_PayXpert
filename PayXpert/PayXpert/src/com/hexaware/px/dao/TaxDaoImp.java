/*
 * @author: Mithra
 * Date: 18/04/2025
 * Description: This class implements the ITaxDao interface to perform
 *              database operations related to Tax such as calculating, 
 *              fetching, and deleting tax records.
 */

package com.hexaware.px.dao;

import com.hexaware.px.entity.Tax;
import com.hexaware.px.exception.TaxNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaxDaoImp implements ITaxDao {

    public TaxDaoImp() {
    }

    /**
     * Calculates tax for an employee for a given tax year.
     * @param employeeId Employee ID for which tax is calculated
     * @param taxYear Tax year for which tax is calculated
     * @return Tax object containing calculated tax details
     */
    public Tax calculateTax(int employeeId, int taxYear) {
        Tax tax = null;

        double taxableIncome = 50000.0; // Placeholder, should be calculated based on employee's income
        double taxAmount = taxableIncome * 0.2;

        try (Connection conn = DBUtil.getDBConnection()) {
            String insert = "INSERT INTO tax (employeeID, taxYear, taxableIncome, taxAmount) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, employeeId);
            pstmt.setInt(2, taxYear);
            pstmt.setDouble(3, taxableIncome);
            pstmt.setDouble(4, taxAmount);

            int count = pstmt.executeUpdate();
            if (count > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int taxId = rs.getInt(1);
                    tax = new Tax(taxId, employeeId, taxYear, taxableIncome, taxAmount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tax;
    }

    /**
     * Retrieves a tax record by its tax ID.
     * @param taxId Tax ID
     * @return Tax object
     * @throws TaxNotFoundException 
     */
    public Tax getTaxById(int taxId) throws TaxNotFoundException {
        Tax tax = null;

        try (Connection conn = DBUtil.getDBConnection()) {
            String select = "SELECT * FROM tax WHERE taxID = ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setInt(1, taxId);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new TaxNotFoundException();
            }

            int employeeID = rs.getInt("employeeID");
            int taxYear = rs.getInt("taxYear");
            double taxableIncome = rs.getDouble("taxableIncome");
            double taxAmount = rs.getDouble("taxAmount");

            tax = new Tax(taxId, employeeID, taxYear, taxableIncome, taxAmount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tax;
    }

    /**
     * Retrieves all tax records for a specific employee.
     * @param employeeId Employee ID
     * @return List of Tax objects for the employee
     */
    public List<Tax> getTaxesForEmployee(int employeeId) {
        List<Tax> taxList = new ArrayList<>();

        try (Connection conn = DBUtil.getDBConnection()) {
            String select = "SELECT * FROM tax WHERE employeeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int taxID = rs.getInt("taxID");
                int taxYear = rs.getInt("taxYear");
                double taxableIncome = rs.getDouble("taxableIncome");
                double taxAmount = rs.getDouble("taxAmount");

                Tax tax = new Tax(taxID, employeeId, taxYear, taxableIncome, taxAmount);
                taxList.add(tax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taxList;
    }

    /**
     * Retrieves all tax records for a specific year.
     * @param taxYear Tax year
     * @return List of Tax objects for the year
     */
    public List<Tax> getTaxesForYear(int taxYear) {
        List<Tax> taxList = new ArrayList<>();

        try (Connection conn = DBUtil.getDBConnection()) {
            String select = "SELECT * FROM tax WHERE taxYear = ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setInt(1, taxYear);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int taxID = rs.getInt("taxID");
                int employeeID = rs.getInt("employeeID");
                double taxableIncome = rs.getDouble("taxableIncome");
                double taxAmount = rs.getDouble("taxAmount");

                Tax tax = new Tax(taxID, employeeID, taxYear, taxableIncome, taxAmount);
                taxList.add(tax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taxList;
    }
}

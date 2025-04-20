package com.hexaware.px.dao;

import com.hexaware.px.entity.PayRoll;
import com.hexaware.px.exception.PayRollGenerationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PayRollDaoImp implements IPayRollDao {

    public PayRoll generatePayroll(int employeeId, java.util.Date startDate, java.util.Date endDate) throws PayRollGenerationException {
        PayRoll payroll = null;

        // Set default values for the payroll
        double baseSalary = 30000.0;
        double overtimePay = 2000.0;  // Example overtime pay
        double deductions = 500.0;  // Example deductions
        double netSalary = baseSalary + overtimePay - deductions;

        try {
            Connection conn = DBUtil.getDBConnection();
            String insert = "INSERT INTO payroll (employeeId, payPeriodStartDate, payPeriodEndDate, basicSalary, overtimePay, deductions, netSalary) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, employeeId);
            pstmt.setDate(2, new Date(startDate.getTime()));
            pstmt.setDate(3, new Date(endDate.getTime()));
            pstmt.setDouble(4, baseSalary);
            pstmt.setDouble(5, overtimePay);
            pstmt.setDouble(6, deductions);
            pstmt.setDouble(7, netSalary);

            int count = pstmt.executeUpdate();
            if (count > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int payrollId = rs.getInt(1);
                    payroll = new PayRoll(payrollId, employeeId, startDate, endDate, baseSalary, overtimePay, deductions, netSalary);
                }
            } else {
                throw new PayRollGenerationException();
            }

        } catch (SQLException e) {
            throw new PayRollGenerationException();
        }

        return payroll;
    }

    public PayRoll getPayrollById(int payrollId) {
        PayRoll payroll = null;

        try {
            Connection conn = DBUtil.getDBConnection();
            String select = "SELECT * FROM payroll WHERE payrollId = ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setInt(1, payrollId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int employeeId = rs.getInt("employeeId");
                java.util.Date payPeriodStartDate = rs.getDate("payPeriodStartDate");
                java.util.Date payPeriodEndDate = rs.getDate("payPeriodEndDate");
                double baseSalary = rs.getDouble("basicSalary");
                double overtimePay = rs.getDouble("overtimePay");
                double deductions = rs.getDouble("deductions");
                double netSalary = rs.getDouble("netSalary");

                payroll = new PayRoll(payrollId, employeeId, payPeriodStartDate, payPeriodEndDate, baseSalary, overtimePay, deductions, netSalary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payroll;
    }

    public List<PayRoll> getPayrollsForEmployee(int employeeId) {
        List<PayRoll> payrollList = new ArrayList<>();

        try {
            Connection conn = DBUtil.getDBConnection();
            String select = "SELECT * FROM payroll WHERE employeeId = ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int payrollId = rs.getInt("payrollId");
                java.util.Date payPeriodStartDate = rs.getDate("payPeriodStartDate");
                java.util.Date payPeriodEndDate = rs.getDate("payPeriodEndDate");
                double baseSalary = rs.getDouble("basicSalary");
                double overtimePay = rs.getDouble("overtimePay");
                double deductions = rs.getDouble("deductions");
                double netSalary = rs.getDouble("netSalary");

                PayRoll payroll = new PayRoll(payrollId, employeeId, payPeriodStartDate, payPeriodEndDate, baseSalary, overtimePay, deductions, netSalary);
                payrollList.add(payroll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payrollList;
    }

    public List<PayRoll> getPayrollsForPeriod(java.util.Date startDate, java.util.Date endDate) {
        List<PayRoll> payrollList = new ArrayList<>();

        try {
            Connection conn = DBUtil.getDBConnection();
            String select = "SELECT * FROM payroll WHERE payPeriodStartDate >= ? AND payPeriodEndDate <= ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setDate(1, new Date(startDate.getTime()));
            pstmt.setDate(2, new Date(endDate.getTime()));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int payrollId = rs.getInt("payrollId");
                int employeeId = rs.getInt("employeeId");
                java.util.Date payPeriodStartDate = rs.getDate("payPeriodStartDate");
                java.util.Date payPeriodEndDate = rs.getDate("payPeriodEndDate");
                double baseSalary = rs.getDouble("basicSalary");
                double overtimePay = rs.getDouble("overtimePay");
                double deductions = rs.getDouble("deductions");
                double netSalary = rs.getDouble("netSalary");

                PayRoll payroll = new PayRoll(payrollId, employeeId, payPeriodStartDate, payPeriodEndDate, baseSalary, overtimePay, deductions, netSalary);
                payrollList.add(payroll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payrollList;
    }
}

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

        double baseSalary = 30000.0;
        double bonus = 5000.0;
        double totalPay = baseSalary + bonus;

        try {
            Connection conn = DBUtil.getDBConnection();
            String insert = "INSERT INTO payroll (employeeId, startDate, endDate, baseSalary, bonus, totalPay) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1, employeeId);
            pstmt.setDate(2, new Date(startDate.getTime()));
            pstmt.setDate(3, new Date(endDate.getTime()));
            pstmt.setDouble(4, baseSalary);
            pstmt.setDouble(5, bonus);
            pstmt.setDouble(6, totalPay);

            int count = pstmt.executeUpdate();
            if (count > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int payrollId = rs.getInt(1);
                    payroll = new PayRoll(payrollId, employeeId, startDate, endDate, baseSalary, bonus, totalPay, totalPay);
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
                java.util.Date startDate = rs.getDate("startDate");
                java.util.Date endDate = rs.getDate("endDate");
                double baseSalary = rs.getDouble("baseSalary");
                double bonus = rs.getDouble("bonus");
                double totalPay = rs.getDouble("totalPay");

                payroll = new PayRoll(payrollId, employeeId, startDate, endDate, baseSalary, bonus, totalPay, totalPay);
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
                java.util.Date startDate = rs.getDate("startDate");
                java.util.Date endDate = rs.getDate("endDate");
                double baseSalary = rs.getDouble("baseSalary");
                double bonus = rs.getDouble("bonus");
                double totalPay = rs.getDouble("totalPay");

                PayRoll payroll = new PayRoll(payrollId, employeeId, startDate, endDate, baseSalary, bonus, totalPay, totalPay);
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
            String select = "SELECT * FROM payroll WHERE startDate >= ? AND endDate <= ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setDate(1, new Date(startDate.getTime()));
            pstmt.setDate(2, new Date(endDate.getTime()));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int payrollId = rs.getInt("payrollId");
                int employeeId = rs.getInt("employeeId");
                double baseSalary = rs.getDouble("baseSalary");
                double bonus = rs.getDouble("bonus");
                double totalPay = rs.getDouble("totalPay");

                PayRoll payroll = new PayRoll(payrollId, employeeId, rs.getDate("startDate"), rs.getDate("endDate"), baseSalary, bonus, totalPay, totalPay);
                payrollList.add(payroll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payrollList;
    }
}

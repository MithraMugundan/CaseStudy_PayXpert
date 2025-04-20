package com.hexaware.px.dao;

import com.hexaware.px.entity.Payroll;
import com.hexaware.px.exception.PayrollNotFoundException;
import com.hexaware.px.dao.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayrollDaoImpl implements IPayrollDao {

    @Override
    public Payroll addPayroll(Payroll payroll) {
        String query = "INSERT INTO payroll (employeeID, payPeriodStartDate, payPeriodEndDate, basicSalary, overtimePay, deductions, netSalary) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getDBConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, payroll.getEmployeeID());
            ps.setDate(2, Date.valueOf(payroll.getPayPeriodStartDate()));
            ps.setDate(3, Date.valueOf(payroll.getPayPeriodEndDate()));
            ps.setDouble(4, payroll.getBasicSalary());
            ps.setDouble(5, payroll.getOvertimePay());
            ps.setDouble(6, payroll.getDeductions());
            ps.setDouble(7, payroll.getNetSalary());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating payroll failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    payroll.setPayrollID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating payroll failed, no ID obtained.");
                }
            }
            return payroll;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Payroll getPayrollById(int payrollId) {
        String query = "SELECT * FROM payroll WHERE payrollID = ?";
        try (Connection conn = DBUtil.getDBConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, payrollId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Payroll(
                            rs.getInt("payrollID"),
                            rs.getInt("employeeID"),
                            rs.getDate("payPeriodStartDate").toLocalDate(),
                            rs.getDate("payPeriodEndDate").toLocalDate(),
                            rs.getDouble("basicSalary"),
                            rs.getDouble("overtimePay"),
                            rs.getDouble("deductions"),
                            rs.getDouble("netSalary")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> payrolls = new ArrayList<>();
        String query = "SELECT * FROM payroll WHERE employeeID = ?";
        try (Connection conn = DBUtil.getDBConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    payrolls.add(new Payroll(
                            rs.getInt("payrollID"),
                            rs.getInt("employeeID"),
                            rs.getDate("payPeriodStartDate").toLocalDate(),
                            rs.getDate("payPeriodEndDate").toLocalDate(),
                            rs.getDouble("basicSalary"),
                            rs.getDouble("overtimePay"),
                            rs.getDouble("deductions"),
                            rs.getDouble("netSalary")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }

    @Override
    public List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate) {
        List<Payroll> payrolls = new ArrayList<>();
        String query = "SELECT * FROM payroll WHERE payPeriodStartDate >= ? AND payPeriodEndDate <= ?";
        try (Connection conn = DBUtil.getDBConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    payrolls.add(new Payroll(
                            rs.getInt("payrollID"),
                            rs.getInt("employeeID"),
                            rs.getDate("payPeriodStartDate").toLocalDate(),
                            rs.getDate("payPeriodEndDate").toLocalDate(),
                            rs.getDouble("basicSalary"),
                            rs.getDouble("overtimePay"),
                            rs.getDouble("deductions"),
                            rs.getDouble("netSalary")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }

    @Override
    public void updatePayroll(Payroll payroll) {
        String query = "UPDATE payroll SET payPeriodStartDate = ?, payPeriodEndDate = ?, basicSalary = ?, overtimePay = ?, deductions = ?, netSalary = ? WHERE payrollID = ?";
        try (Connection conn = DBUtil.getDBConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(payroll.getPayPeriodStartDate()));
            ps.setDate(2, Date.valueOf(payroll.getPayPeriodEndDate()));
            ps.setDouble(3, payroll.getBasicSalary());
            ps.setDouble(4, payroll.getOvertimePay());
            ps.setDouble(5, payroll.getDeductions());
            ps.setDouble(6, payroll.getNetSalary());
            ps.setInt(7, payroll.getPayrollID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePayroll(int payrollId) {
        String query = "DELETE FROM payroll WHERE payrollID = ?";
        try (Connection conn = DBUtil.getDBConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, payrollId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

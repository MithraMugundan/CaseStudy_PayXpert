package com.hexaware.px.dao;

import com.hexaware.px.entity.FinancialRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FinancialRecordDaoImp implements IFinancialRecordDao {

    @Override
    public FinancialRecord addFinancialRecord(int employeeId, String description, double amount, String recordType) {
        FinancialRecord record = null;

        try (Connection conn = DBUtil.getDBConnection()) {
            String insert = "INSERT INTO financialrecord (employeeId, recordDate, description, amount, recordType) " +
                            "VALUES (?, CURRENT_DATE, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, employeeId);
            pstmt.setString(2, description);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, recordType);

            int count = pstmt.executeUpdate();
            if (count > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int recordId = rs.getInt(1);
                    Date recordDate = new Date(System.currentTimeMillis());
                    record = new FinancialRecord(recordId, employeeId, recordDate, description, amount, recordType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return record;
    }

    @Override
    public FinancialRecord getFinancialRecordById(int recordId) {
        FinancialRecord record = null;

        try (Connection conn = DBUtil.getDBConnection()) {
            String select = "SELECT * FROM financialrecord WHERE recordId = ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setInt(1, recordId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int employeeId = rs.getInt("employeeId");
                Date recordDate = rs.getDate("recordDate");
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                String recordType = rs.getString("recordType");

                record = new FinancialRecord(recordId, employeeId, recordDate, description, amount, recordType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return record;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        List<FinancialRecord> recordList = new ArrayList<>();

        try (Connection conn = DBUtil.getDBConnection()) {
            String select = "SELECT * FROM financialrecord WHERE employeeId = ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int recordId = rs.getInt("recordId");
                Date recordDate = rs.getDate("recordDate");
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                String recordType = rs.getString("recordType");

                FinancialRecord record = new FinancialRecord(recordId, employeeId, recordDate, description, amount, recordType);
                recordList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recordList;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForDate(java.util.Date recordDate) {
        List<FinancialRecord> recordList = new ArrayList<>();

        try (Connection conn = DBUtil.getDBConnection()) {
            String select = "SELECT * FROM financialrecord WHERE recordDate = ?";
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setDate(1, new Date(recordDate.getTime())); // Convert util.Date to sql.Date
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int recordId = rs.getInt("recordId");
                int employeeId = rs.getInt("employeeId");
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                String recordType = rs.getString("recordType");

                FinancialRecord record = new FinancialRecord(recordId, employeeId, new Date(recordDate.getTime()), description, amount, recordType);
                recordList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recordList;
    }
}

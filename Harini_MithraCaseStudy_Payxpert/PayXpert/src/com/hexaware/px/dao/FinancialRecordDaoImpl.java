package com.hexaware.px.dao;

import com.hexaware.px.entity.FinancialRecord;
import com.hexaware.px.dao.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinancialRecordDaoImpl implements IFinancialRecordDao {

    @Override
    public FinancialRecord getFinancialRecordById(int id) {
        String query = "SELECT * FROM financial_records WHERE record_id = ?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new FinancialRecord(
                        resultSet.getInt("record_id"),
                        resultSet.getInt("employeeId"),
                        resultSet.getDate("record_date").toLocalDate(),
                        resultSet.getString("description"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("record_type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if the record is not found
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        List<FinancialRecord> financialRecords = new ArrayList<>();
        String query = "SELECT * FROM financial_records WHERE employeeId = ?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FinancialRecord financialRecord = new FinancialRecord(
                        resultSet.getInt("record_id"),
                        resultSet.getInt("employeeId"),
                        resultSet.getDate("record_date").toLocalDate(),
                        resultSet.getString("description"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("record_type")
                );
                financialRecords.add(financialRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return financialRecords;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForYear(int year) {
        List<FinancialRecord> financialRecords = new ArrayList<>();
        String query = "SELECT * FROM financial_records WHERE YEAR(record_date) = ?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FinancialRecord financialRecord = new FinancialRecord(
                        resultSet.getInt("record_id"),
                        resultSet.getInt("employeeId"),
                        resultSet.getDate("record_date").toLocalDate(),
                        resultSet.getString("description"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("record_type")
                );
                financialRecords.add(financialRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return financialRecords;
    }

    @Override
    public FinancialRecord addFinancialRecord(FinancialRecord financialRecord) {
        String query = "INSERT INTO financial_records (employeeId, record_date, description, amount, record_type) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, financialRecord.getEmployeeID());
            preparedStatement.setDate(2, Date.valueOf(financialRecord.getRecordDate()));
            preparedStatement.setString(3, financialRecord.getDescription());
            preparedStatement.setDouble(4, financialRecord.getAmount());
            preparedStatement.setString(5, financialRecord.getRecordType());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating financial record failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    financialRecord.setRecordID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating financial record failed, no ID obtained.");
                }
            }
            return financialRecord;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateFinancialRecord(FinancialRecord financialRecord) {
        String query = "UPDATE financial_records SET employeeId = ?, record_date = ?, description = ?, amount = ?, record_type = ? WHERE record_id = ?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, financialRecord.getEmployeeID());
            preparedStatement.setDate(2, Date.valueOf(financialRecord.getRecordDate()));
            preparedStatement.setString(3, financialRecord.getDescription());
            preparedStatement.setDouble(4, financialRecord.getAmount());
            preparedStatement.setString(5, financialRecord.getRecordType());
            preparedStatement.setInt(6, financialRecord.getRecordID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFinancialRecord(int recordId) {
        String query = "DELETE FROM financial_records WHERE record_id = ?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recordId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

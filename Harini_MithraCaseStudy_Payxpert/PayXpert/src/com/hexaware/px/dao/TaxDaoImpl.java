package com.hexaware.px.dao;

import com.hexaware.px.entity.Tax;
import com.hexaware.px.exception.TaxNotFoundException;
import com.hexaware.px.dao.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxDaoImpl implements ITaxDao {

    @Override
    public double calculateTax(int employeeId, double salary) {
        double taxAmount = 0.0;
        if (salary <= 50000) {
            taxAmount = salary * 0.1;
        } else if (salary <= 100000) {
            taxAmount = salary * 0.2;
        } else {
            taxAmount = salary * 0.3;
        }
        return taxAmount;
    }

    @Override
    public Tax getTaxById(int id) throws TaxNotFoundException {
        String query = "SELECT * FROM taxes WHERE tax_id = ?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Tax(
                        resultSet.getInt("tax_id"),
                        resultSet.getInt("employee_id"),
                        resultSet.getInt("tax_year"),
                        resultSet.getDouble("taxable_income"),
                        resultSet.getDouble("tax_amount")
                );
            } else {
                throw new TaxNotFoundException("Tax record with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        List<Tax> taxes = new ArrayList<>();
        String query = "SELECT * FROM taxes WHERE employee_id = ?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tax tax = new Tax(
                        resultSet.getInt("tax_id"),
                        resultSet.getInt("employee_id"),
                        resultSet.getInt("tax_year"),
                        resultSet.getDouble("taxable_income"),
                        resultSet.getDouble("tax_amount")
                );
                taxes.add(tax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taxes;
    }

    @Override
    public List<Tax> getTaxesForYear(int year) {
        List<Tax> taxes = new ArrayList<>();
        String query = "SELECT * FROM taxes WHERE tax_year = ?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tax tax = new Tax(
                        resultSet.getInt("tax_id"),
                        resultSet.getInt("employee_id"),
                        resultSet.getInt("tax_year"),
                        resultSet.getDouble("taxable_income"),
                        resultSet.getDouble("tax_amount")
                );
                taxes.add(tax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taxes;
    }

    @Override
    public List<Tax> getAllTaxes() {
        List<Tax> taxes = new ArrayList<>();
        String query = "SELECT * FROM taxes";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tax tax = new Tax(
                        resultSet.getInt("tax_id"),
                        resultSet.getInt("employee_id"),
                        resultSet.getInt("tax_year"),
                        resultSet.getDouble("taxable_income"),
                        resultSet.getDouble("tax_amount")
                );
                taxes.add(tax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taxes;
    }
}

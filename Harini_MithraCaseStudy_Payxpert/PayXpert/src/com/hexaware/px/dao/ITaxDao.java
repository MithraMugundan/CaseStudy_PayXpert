package com.hexaware.px.dao;

import com.hexaware.px.entity.Tax;
import com.hexaware.px.exception.TaxNotFoundException;

import java.util.List;

public interface ITaxDao {
    double calculateTax(int employeeId, double salary);
    Tax getTaxById(int id) throws TaxNotFoundException;
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int year);
    List<Tax> getAllTaxes();  // New method for getting all taxes
}

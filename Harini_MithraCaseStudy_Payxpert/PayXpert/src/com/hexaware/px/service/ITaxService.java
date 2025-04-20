package com.hexaware.px.service;

import com.hexaware.px.entity.Tax;
import com.hexaware.px.exception.TaxNotFoundException;

import java.util.List;

public interface ITaxService {
    double calculateTax(int employeeId, double salary);  // Ensure this method is in ITaxService
    Tax getTaxById(int id) throws TaxNotFoundException;
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int year);
    List<Tax> getAllTaxes();
}

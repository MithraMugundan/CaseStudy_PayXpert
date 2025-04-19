
/* @author: Mithra |
 *  Date: 19/04/2025 |
 *   Description: Interface for Tax service, defining methods for calculating and retrieving tax records for employees. */

package com.hexaware.px.service;

import java.util.List;

import com.hexaware.px.entity.Tax;
import com.hexaware.px.exception.TaxNotFoundException;

public interface ITaxService {
	
	 // Calculate the tax for an employee in a specific tax year
    Tax calculateTax(int employeeId, int taxYear);

    // Retrieve a tax record by its unique tax ID
    Tax getTaxById(int taxId) throws TaxNotFoundException;

    // Get a list of tax records for a specific employee
    List<Tax> getTaxesForEmployee(int employeeId);

    // Get a list of tax records for a specific year
    List<Tax> getTaxesForYear(int taxYear);

}

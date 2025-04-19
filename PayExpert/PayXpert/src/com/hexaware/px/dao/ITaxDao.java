/*

 * @author: Mithra
 * Date: 18/04/2025
 * Description: Tax interface class for defining data access operations related to Tax entities.
 */

package com.hexaware.px.dao;

import com.hexaware.px.entity.Tax;
import com.hexaware.px.exception.TaxNotFoundException;

import java.util.List;

public interface ITaxDao {

    // Calculate the tax for an employee in a specific tax year
    Tax calculateTax(int employeeId, int taxYear);

    // Retrieve a tax record by its unique tax ID
    Tax getTaxById(int taxId) throws TaxNotFoundException;

    // Get a list of tax records for a specific employee
    List<Tax> getTaxesForEmployee(int employeeId);

    // Get a list of tax records for a specific year
    List<Tax> getTaxesForYear(int taxYear);
}

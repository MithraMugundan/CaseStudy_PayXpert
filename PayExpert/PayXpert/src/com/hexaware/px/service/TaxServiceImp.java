
/* @author: Mithra 
 *  Date: 19/04/2025 
 *  Description: Implementation of TaxService interface for managing tax-related operations such as calculating, fetching taxes, and validating tax data. */

package com.hexaware.px.service;

import com.hexaware.px.dao.ITaxDao;
import com.hexaware.px.dao.TaxDaoImp;
import com.hexaware.px.entity.Tax;
import com.hexaware.px.exception.TaxNotFoundException;

import java.util.List;

public class TaxServiceImp implements ITaxService {

    ITaxDao dao = new TaxDaoImp();

    public TaxServiceImp() {
    }

    @Override
    public Tax calculateTax(int employeeId, int taxYear) {
        return this.dao.calculateTax(employeeId, taxYear);
    }

    @Override
    public Tax getTaxById(int taxId) throws TaxNotFoundException {
        return this.dao.getTaxById(taxId);
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        return this.dao.getTaxesForEmployee(employeeId);
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        return this.dao.getTaxesForYear(taxYear);
    }

    public static boolean validateData(Tax tax) {
        boolean flag = false;
        if (tax.getEmployeeID() > 0 && tax.getTaxYear() > 0 && tax.getTaxableIncome() > 0.0 && tax.getTaxAmount() > 0.0) {
            flag = true;
        }

        return flag;
    }
}

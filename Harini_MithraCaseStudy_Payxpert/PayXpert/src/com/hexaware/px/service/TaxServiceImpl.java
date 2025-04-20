package com.hexaware.px.service;

import com.hexaware.px.dao.ITaxDao;
import com.hexaware.px.dao.TaxDaoImpl;
import com.hexaware.px.entity.Tax;
import com.hexaware.px.exception.TaxNotFoundException;

import java.util.List;

public class TaxServiceImpl implements ITaxService {

    private ITaxDao taxDao = new TaxDaoImpl();

    public TaxServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public TaxServiceImpl(TaxDaoImpl taxDaoImpl) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public double calculateTax(int employeeId, double salary) {
        return taxDao.calculateTax(employeeId, salary);
    }

    @Override
    public Tax getTaxById(int id) throws TaxNotFoundException {
        return taxDao.getTaxById(id);
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        return taxDao.getTaxesForEmployee(employeeId);
    }

    @Override
    public List<Tax> getTaxesForYear(int year) {
        return taxDao.getTaxesForYear(year);
    }

    @Override
    public List<Tax> getAllTaxes() {
        return taxDao.getAllTaxes();
    }
}

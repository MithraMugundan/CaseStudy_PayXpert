package com.hexaware.px.service;

import com.hexaware.px.dao.FinancialRecordDaoImpl;
import com.hexaware.px.dao.IFinancialRecordDao;
import com.hexaware.px.entity.FinancialRecord;

import java.util.List;

public class FinancialRecordServiceImpl implements IFinancialRecordService {

    private IFinancialRecordDao financialRecordDao;

    public FinancialRecordServiceImpl() {
        this.financialRecordDao = new FinancialRecordDaoImpl();
    }

    public FinancialRecordServiceImpl(IFinancialRecordDao financialRecordDao2) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public FinancialRecord getFinancialRecordById(int id) {
        return financialRecordDao.getFinancialRecordById(id);
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        return financialRecordDao.getFinancialRecordsForEmployee(employeeId);
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForYear(int year) {
        return financialRecordDao.getFinancialRecordsForYear(year);
    }

    @Override
    public FinancialRecord addFinancialRecord(FinancialRecord financialRecord) {
        return financialRecordDao.addFinancialRecord(financialRecord);
    }

    @Override
    public void updateFinancialRecord(FinancialRecord financialRecord) {
        financialRecordDao.updateFinancialRecord(financialRecord);
    }

    @Override
    public void deleteFinancialRecord(int recordId) {
        financialRecordDao.deleteFinancialRecord(recordId);
    }
}

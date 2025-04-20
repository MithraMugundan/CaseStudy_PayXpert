package com.hexaware.px.service;

import com.hexaware.px.dao.FinancialRecordDaoImp;
import com.hexaware.px.dao.IFinancialRecordDao;
import com.hexaware.px.entity.FinancialRecord;

import java.util.Date;
import java.util.List;

public class FinancialRecordServiceImp implements IFinancialRecordService {

    IFinancialRecordDao dao = new FinancialRecordDaoImp();

    public FinancialRecordServiceImp() {
    }

    @Override
    public FinancialRecord addFinancialRecord(int employeeId, String description, double amount, String recordType) {
        return this.dao.addFinancialRecord(employeeId, description, amount, recordType);
    }

    @Override
    public FinancialRecord getFinancialRecordById(int recordId) {
        return this.dao.getFinancialRecordById(recordId);
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        return this.dao.getFinancialRecordsForEmployee(employeeId);
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForDate(Date recordDate) {
        return this.dao.getFinancialRecordsForDate(recordDate);
    }

    //validating datas 
    public static boolean validateData(FinancialRecord record) {
        boolean flag = false;
        if (record.getEmployeeID() > 0 &&
            record.getDescription() != null && !record.getDescription().isEmpty() &&
            record.getAmount() > 0.0 &&
            record.getRecordType() != null && !record.getRecordType().isEmpty()) {
            flag = true;
        }

        return flag;
    }
}

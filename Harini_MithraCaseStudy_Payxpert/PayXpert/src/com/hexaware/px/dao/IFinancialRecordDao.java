package com.hexaware.px.dao;

import com.hexaware.px.entity.FinancialRecord;

import java.util.List;

public interface IFinancialRecordDao {
    FinancialRecord getFinancialRecordById(int id);
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecord> getFinancialRecordsForYear(int year);
    FinancialRecord addFinancialRecord(FinancialRecord financialRecord);
    void updateFinancialRecord(FinancialRecord financialRecord);
    void deleteFinancialRecord(int recordId);
}

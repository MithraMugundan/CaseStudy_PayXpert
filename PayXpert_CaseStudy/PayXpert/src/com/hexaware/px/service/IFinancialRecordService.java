package com.hexaware.px.service;

import java.util.Date;
import java.util.List;

import com.hexaware.px.entity.FinancialRecord;

public interface IFinancialRecordService {

	FinancialRecord addFinancialRecord(int employeeId, String description, double amount, String recordType);
    FinancialRecord getFinancialRecordById(int recordId);
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecord> getFinancialRecordsForDate(Date recordDate);
}

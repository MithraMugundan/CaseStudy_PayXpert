
package com.hexaware.px.dao;

import com.hexaware.px.entity.FinancialRecord;
import java.util.Date;
import java.util.List;

public interface IFinancialRecordDao {
    FinancialRecord addFinancialRecord(int employeeId, String description, double amount, String recordType);
    FinancialRecord getFinancialRecordById(int recordId);
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecord> getFinancialRecordsForDate(Date recordDate);
}

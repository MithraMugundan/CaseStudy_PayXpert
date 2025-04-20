package com.hexaware.px.dao;

import com.hexaware.px.entity.Payroll;
import java.time.LocalDate;
import java.util.List;

public interface IPayrollDao {
    Payroll addPayroll(Payroll payroll);
    Payroll getPayrollById(int payrollId);
    List<Payroll> getPayrollsForEmployee(int employeeId);
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
    void updatePayroll(Payroll payroll);
    void deletePayroll(int payrollId);
}

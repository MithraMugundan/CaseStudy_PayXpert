package com.hexaware.px.service;

import com.hexaware.px.entity.Payroll;
import com.hexaware.px.exception.PayrollNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface IPayrollService {
    Payroll addPayroll(Payroll payroll);
    Payroll getPayrollById(int payrollId) throws PayrollNotFoundException;  // Declare exception here
    List<Payroll> getPayrollsForEmployee(int employeeId);
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
    void updatePayroll(Payroll payroll);
    void deletePayroll(int payrollId) throws PayrollNotFoundException;  // Declare exception here
    
    Payroll generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate, 
                            double basicSalary, double overtimePay, double deductions);
}

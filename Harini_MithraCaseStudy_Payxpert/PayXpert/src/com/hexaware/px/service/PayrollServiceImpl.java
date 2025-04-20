package com.hexaware.px.service;

import com.hexaware.px.dao.IPayrollDao;
import com.hexaware.px.dao.PayrollDaoImpl;
import com.hexaware.px.entity.Payroll;
import com.hexaware.px.exception.PayrollNotFoundException;

import java.time.LocalDate;
import java.util.List;

public class PayrollServiceImpl implements IPayrollService {

    private IPayrollDao payrollDao;

    public PayrollServiceImpl() {
        this.payrollDao = new PayrollDaoImpl(); // Assuming PayrollDaoImpl is your concrete implementation
    }

    public PayrollServiceImpl(IPayrollDao payrollDao2) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public Payroll generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate,
                                   double basicSalary, double overtimePay, double deductions) {
        double netSalary = basicSalary + overtimePay - deductions;
        Payroll payroll = new Payroll(0, employeeId, startDate, endDate, basicSalary, overtimePay, deductions, netSalary);

        // Add the payroll to the database using the DAO
        return payrollDao.addPayroll(payroll);
    }

    @Override
    public Payroll getPayrollById(int payrollId) throws PayrollNotFoundException {
        Payroll payroll = payrollDao.getPayrollById(payrollId);
        if (payroll == null) {
            throw new PayrollNotFoundException("Payroll with ID " + payrollId + " not found.");
        }
        return payroll;
    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        return payrollDao.getPayrollsForEmployee(employeeId);
    }

    @Override
    public List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate) {
        return payrollDao.getPayrollsForPeriod(startDate, endDate);
    }

    @Override
    public void updatePayroll(Payroll payroll) {
        payrollDao.updatePayroll(payroll);
    }

    @Override
    public void deletePayroll(int payrollId) throws PayrollNotFoundException {
        Payroll payroll = payrollDao.getPayrollById(payrollId);
        if (payroll == null) {
            throw new PayrollNotFoundException("Payroll with ID " + payrollId + " not found.");
        }
        payrollDao.deletePayroll(payrollId);
    }

    @Override
    public Payroll addPayroll(Payroll payroll) {
        return payrollDao.addPayroll(payroll);
    }
}

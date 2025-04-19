package com.hexaware.px.service;

import com.hexaware.px.dao.IPayRollDao;
import com.hexaware.px.dao.PayRollDaoImp;
import com.hexaware.px.entity.PayRoll;
import com.hexaware.px.exception.PayRollGenerationException;

import java.util.Date;
import java.util.List;

public class PayRollServiceImp implements IPayRollService {

    IPayRollDao dao = new PayRollDaoImp();

    public PayRollServiceImp() {
    }

    @Override
    public PayRoll generatePayroll(int employeeId, Date startDate, Date endDate) throws PayRollGenerationException {
        return this.dao.generatePayroll(employeeId, startDate, endDate);
    }

    @Override
    public PayRoll getPayrollById(int payrollId) {
        return this.dao.getPayrollById(payrollId);
    }

    @Override
    public List<PayRoll> getPayrollsForEmployee(int employeeId) {
        return this.dao.getPayrollsForEmployee(employeeId);
    }

    @Override
    public List<PayRoll> getPayrollsForPeriod(Date startDate, Date endDate) {
        return this.dao.getPayrollsForPeriod(startDate, endDate);
    }

    //Validation 
    public static boolean validateData(PayRoll payroll) {
        boolean flag = false;
        if (payroll.getEmployeeID() > 0 &&
            payroll.getPayPeriodStartDate() != null &&
            payroll.getPayPeriodEndDate() != null &&
            payroll.getBasicSalary() >= 0)
            {
            flag = true;
        }

        return flag;
    }
}

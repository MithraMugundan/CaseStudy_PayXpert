package com.hexaware.px.dao;

import com.hexaware.px.entity.PayRoll;
import com.hexaware.px.exception.PayRollGenerationException;

import java.util.Date;
import java.util.List;

public interface IPayRollDao {
    PayRoll generatePayroll(int employeeId, Date startDate, Date endDate) throws PayRollGenerationException;
    PayRoll getPayrollById(int payrollId);
    List<PayRoll> getPayrollsForEmployee(int employeeId);
    List<PayRoll> getPayrollsForPeriod(Date startDate, Date endDate);
}

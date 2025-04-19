package com.hexaware.px.service;

import java.util.Date;
import java.util.List;

import com.hexaware.px.entity.PayRoll;
import com.hexaware.px.exception.PayRollGenerationException;

public interface IPayRollService {
	  PayRoll generatePayroll(int employeeId, Date startDate, Date endDate) throws PayRollGenerationException;
	    PayRoll getPayrollById(int payrollId);
	    List<PayRoll> getPayrollsForEmployee(int employeeId);
	    List<PayRoll> getPayrollsForPeriod(Date startDate, Date endDate);

}

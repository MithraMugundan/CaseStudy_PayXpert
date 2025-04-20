package com.hexaware.px.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.hexaware.px.entity.PayRoll;
import com.hexaware.px.exception.PayRollGenerationException;

public interface IPayRollService {
	  PayRoll generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate) throws PayRollGenerationException;
	    PayRoll getPayrollById(int payrollId);
	    List<PayRoll> getPayrollsForEmployee(int employeeId);
	    List<PayRoll> getPayrollsForPeriod(LocalDate periodStartDate, LocalDate periodEndDate);

}

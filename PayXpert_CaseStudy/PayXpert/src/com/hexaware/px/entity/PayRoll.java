

/*@author: Mithra
 * Date: 12/04/2025
 * Description: Creating PayRoll class.*/
package com.hexaware.px.entity;

import java.util.Date;

public class PayRoll {
	
	 private int payrollID;
	    private int employeeID;
	    private Date payPeriodStartDate;
	    private Date payPeriodEndDate;
	    private double basicSalary;
	    private double overtimePay;
	    private double deductions;
	    private double netSalary;
	    
	    public PayRoll() {}

		public PayRoll(int payrollID, int employeeID, Date payPeriodStartDate, Date payPeriodEndDate,
				double basicSalary, double overtimePay, double deductions, double netSalary) {
			super();
			this.payrollID = payrollID;
			this.employeeID = employeeID;
			this.payPeriodStartDate = payPeriodStartDate;
			this.payPeriodEndDate = payPeriodEndDate;
			this.basicSalary = basicSalary;
			this.overtimePay = overtimePay;
			this.deductions = deductions;
			this.netSalary = netSalary;
		}

		public int getPayrollID() {
			return payrollID;
		}

		public void setPayrollID(int payrollID) {
			this.payrollID = payrollID;
		}

		public int getEmployeeID() {
			return employeeID;
		}

		public void setEmployeeID(int employeeID) {
			this.employeeID = employeeID;
		}

		public Date getPayPeriodStartDate() {
			return payPeriodStartDate;
		}

		public void setPayPeriodStartDate(Date payPeriodStartDate) {
			this.payPeriodStartDate = payPeriodStartDate;
		}

		public Date getPayPeriodEndDate() {
			return payPeriodEndDate;
		}

		public void setPayPeriodEndDate(Date payPeriodEndDate) {
			this.payPeriodEndDate = payPeriodEndDate;
		}

		public double getBasicSalary() {
			return basicSalary;
		}

		public void setBasicSalary(double basicSalary) {
			this.basicSalary = basicSalary;
		}

		public double getOvertimePay() {
			return overtimePay;
		}

		public void setOvertimePay(double overtimePay) {
			this.overtimePay = overtimePay;
		}

		public double getDeductions() {
			return deductions;
		}

		public void setDeductions(double deductions) {
			this.deductions = deductions;
		}

		public double getNetSalary() {
			return netSalary;
		}

		public void setNetSalary(double netSalary) {
			this.netSalary = netSalary;
		}

		@Override
		public String toString() {
			return "PayRoll [payrollID=" + payrollID + ", employeeID=" + employeeID + ", payPeriodStartDate="
					+ payPeriodStartDate + ", payPeriodEndDate=" + payPeriodEndDate + ", basicSalary=" + basicSalary
					+ ", overtimePay=" + overtimePay + ", deductions=" + deductions + ", netSalary=" + netSalary + "]";
		}

}

package com.hexaware.px.entity;

import java.time.LocalDate;

public class Payroll {
    private int payrollID;
    private int employeeID;
    private LocalDate payPeriodStartDate;
    private LocalDate payPeriodEndDate;
    private double basicSalary;
    private double overtimePay;
    private double deductions;
    private double netSalary;

    public Payroll() {}

    public Payroll(int payrollID, int employeeID, LocalDate payPeriodStartDate, LocalDate payPeriodEndDate,
                   double basicSalary, double overtimePay, double deductions, double netSalary) {
        this.payrollID = payrollID;
        this.employeeID = employeeID;
        this.payPeriodStartDate = payPeriodStartDate;
        this.payPeriodEndDate = payPeriodEndDate;
        this.basicSalary = basicSalary;
        this.overtimePay = overtimePay;
        this.deductions = deductions;
        this.netSalary = netSalary;
    }

    // Getters and Setters
    public int getPayrollID() { return payrollID; }
    public void setPayrollID(int payrollID) { this.payrollID = payrollID; }

    public int getEmployeeID() { return employeeID; }
    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }

    public LocalDate getPayPeriodStartDate() { return payPeriodStartDate; }
    public void setPayPeriodStartDate(LocalDate payPeriodStartDate) { this.payPeriodStartDate = payPeriodStartDate; }

    public LocalDate getPayPeriodEndDate() { return payPeriodEndDate; }
    public void setPayPeriodEndDate(LocalDate payPeriodEndDate) { this.payPeriodEndDate = payPeriodEndDate; }

    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    public double getOvertimePay() { return overtimePay; }
    public void setOvertimePay(double overtimePay) { this.overtimePay = overtimePay; }

    public double getDeductions() { return deductions; }
    public void setDeductions(double deductions) { this.deductions = deductions; }

    public double getNetSalary() { return netSalary; }
    public void setNetSalary(double netSalary) { this.netSalary = netSalary; }

    @Override
    public String toString() {
        return "Payroll{" +
                "payrollID=" + payrollID +
                ", employeeID=" + employeeID +
                ", payPeriodStartDate=" + payPeriodStartDate +
                ", payPeriodEndDate=" + payPeriodEndDate +
                ", basicSalary=" + basicSalary +
                ", overtimePay=" + overtimePay +
                ", deductions=" + deductions +
                ", netSalary=" + netSalary +
                '}';
    }
}

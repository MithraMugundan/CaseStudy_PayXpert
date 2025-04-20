package com.hexaware.px.test;

import com.hexaware.px.entity.Payroll;
import com.hexaware.px.exception.PayrollNotFoundException;
import com.hexaware.px.service.IPayrollService;
import com.hexaware.px.service.ITaxService;
import com.hexaware.px.service.PayrollServiceImpl;
import com.hexaware.px.service.TaxServiceImpl;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class IPayrollServiceTest {

    private IPayrollService payrollService;
    private ITaxService taxService;

    @Before
    public void setUp() {
        payrollService = new PayrollServiceImpl();
        taxService = new TaxServiceImpl();
    }

    // Test Case 1: Calculate Net Salary for Employee
    @Test
    public void testCalculateNetSalaryForEmployee() {
        double basicSalary = 5000.00;
        double overtimePay = 200.00;
        double allowances = 100.00;

        double grossSalary = basicSalary + overtimePay + allowances;
        double tax = taxService.calculateTax(1, grossSalary);
        double expectedNetSalary = grossSalary - tax;

        Payroll payroll = payrollService.generatePayroll(
                1,
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 31),
                basicSalary,
                overtimePay,
                allowances
        );

        assertNotNull(payroll);
        assertEquals(expectedNetSalary, payroll.getNetSalary(), 0.01);
    }

    // Test Case 2: Verify Tax Calculation for High-Income Employee
    @Test
    public void testVerifyTaxCalculationForHighIncomeEmployee() {
        double highIncome = 150000.00;
        double expectedTax = highIncome * 0.3; // Assuming 30% slab

        double actualTax = taxService.calculateTax(1, highIncome);
        assertEquals(expectedTax, actualTax, 0.01);
    }

    // Test Case 3: Process Payroll for Multiple Employees
    @Test
    public void testProcessPayrollForMultipleEmployees() {
        LocalDate start = LocalDate.of(2024, 3, 1);
        LocalDate end = LocalDate.of(2024, 3, 31);

        payrollService.generatePayroll(1, start, end, 5000.00, 200.00, 100.00);
        payrollService.generatePayroll(2, start, end, 6000.00, 250.00, 150.00);

        List<Payroll> payrolls = payrollService.getPayrollsForPeriod(start, end);

        assertNotNull(payrolls);
        assertTrue("Payroll list should contain at least 2 entries", payrolls.size() >= 2);
    }

    // Test Case 4: Error Handling for Invalid Employee Data
    @Test
    public void testErrorHandlingForInvalidPayrollId() {
        try {
            payrollService.getPayrollById(9999); // Invalid ID
            fail("Expected PayrollNotFoundException");
        } catch (PayrollNotFoundException ex) {
            assertEquals("Payroll not found", ex.getMessage());
        }
    }
}

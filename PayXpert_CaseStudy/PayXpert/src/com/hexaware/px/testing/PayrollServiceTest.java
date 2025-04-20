/*package com.hexaware.px.testing;

import com.hexaware.px.entity.PayRoll;
import com.hexaware.px.service.PayRollServiceImp;
import com.hexaware.px.service.IPayRollService;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PayrollServiceTest {

    private IPayRollService payrollService;

    @BeforeEach
    void setUp() {
        payrollService = new PayRollServiceImp();
    }

    @Test
    void testCalculateGrossSalaryForEmployee() {
        double basicSalary = 5000.0;
        double overtimePay = 200.0;
        double expectedGross = 5200.0;

        double actualGross = payrollService.calculateGrossSalary(basicSalary, overtimePay);
        assertEquals(expectedGross, actualGross, "Gross salary should be basic + overtime");
    }

    @Test
    void testCalculateNetSalaryAfterDeductions() {
        double gross = 6000.0;
        double deductions = 800.0;
        double expectedNet = 5200.0;

        double actualNet = payrollService.calculateNetSalary(gross, deductions);
        assertEquals(expectedNet, actualNet, "Net salary should be gross - deductions");
    }

    @Test
    void testVerifyTaxCalculationForHighIncomeEmployee() {
        double income = 120000.0;
        double expectedTax = income * 0.20; // Example flat 20% tax
        double actualTax = payrollService.calculateTax(income);

        assertEquals(expectedTax, actualTax, 0.01, "High-income tax calculation failed");
    }

    @Test
    void testProcessPayrollForMultipleEmployees() {
        PayRoll p1 = new PayRoll();
        PayRoll p2 = new PayRoll();

        List<PayRoll> input = Arrays.asList(p1, p2);
        List<PayRoll> result = payrollService.processPayrollForEmployees(input);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(3800, result.get(0).getNetSalary());
        assertEquals(4600, result.get(1).getNetSalary());
    }

    
}*/
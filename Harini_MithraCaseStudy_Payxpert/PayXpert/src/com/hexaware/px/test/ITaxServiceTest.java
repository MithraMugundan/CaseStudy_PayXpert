package com.hexaware.px.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.hexaware.px.service.ITaxService;
import com.hexaware.px.service.TaxServiceImpl; // Make sure this class exists

public class ITaxServiceTest {

    private ITaxService taxService;

    @Before
    public void setUp() {
        taxService = new TaxServiceImpl(); // Use actual implementation
    }

    // Test Case 3: Verify Tax Calculation for High-Income Employee
    @Test
    public void testVerifyTaxCalculationForHighIncomeEmployee() {
        double highIncomeSalary = 150000.00;
        double expectedTax = highIncomeSalary * 0.3;  // Assuming 30% tax rate for high income

        double actualTax = taxService.calculateTax(1, highIncomeSalary);

        assertEquals("Tax for high-income employee should be calculated correctly", expectedTax, actualTax, 0.01);
    }
}

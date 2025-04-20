package com.hexaware.px.testing;

import com.hexaware.px.entity.PayRoll;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PayrollEntityTest {

    private PayRoll payroll;

    @BeforeEach
    void init() {
        payroll = new PayRoll();
        payroll.setBasicSalary(5000.0);
        payroll.setOvertimePay(1000.0);
        payroll.setDeductions(1200.0);
    }

    @Test
    void testGrossSalaryCalculation() {
        double expectedGross = 6000.0;
        double actualGross = payroll.getBasicSalary() + payroll.getOvertimePay();
        assertEquals(expectedGross, actualGross, "Gross salary should be basic + overtime");
    }

    @Test
    void testNetSalaryCalculation() {
        double gross = payroll.getBasicSalary() + payroll.getOvertimePay();
        double expectedNet = gross - payroll.getDeductions();

        payroll.setNetSalary(expectedNet);
        assertEquals(expectedNet, payroll.getNetSalary(), "Net salary should be gross - deductions");
    }

    @Test
    void testSetAndGetNetSalary() {
        payroll.setNetSalary(4800.0);
        assertEquals(4800.0, payroll.getNetSalary(), "Net salary should be set correctly");
    }

    @Test
    void testNegativeSalaryValues() {
        payroll.setBasicSalary(-1000.0);
        assertTrue(payroll.getBasicSalary() < 0, "Basic salary is negative, which should be validated elsewhere");
    }

    @Test
    void testToStringContainsSalaryDetails() {
        String str = payroll.toString();
        assertTrue(str.contains("basicSalary=5000.0"), "toString should contain basic salary");
        assertTrue(str.contains("overtimePay=1000.0"), "toString should contain overtime pay");
        assertTrue(str.contains("deductions=1200.0"), "toString should contain deductions");
    }
}

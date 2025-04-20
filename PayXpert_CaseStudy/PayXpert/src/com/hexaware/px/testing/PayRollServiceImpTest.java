package com.hexaware.px.testing;

import com.hexaware.px.dao.IPayRollDao;
import com.hexaware.px.entity.PayRoll;
import com.hexaware.px.exception.PayRollGenerationException;
import com.hexaware.px.service.PayRollServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class PayRollServiceImpTest {

    private IPayRollDao payRollDao;
    private PayRollServiceImp payRollService;

  

    @Test
    public void testValidateData_Valid() {
        PayRoll payRoll = new PayRoll(1, 0, new Date(), new Date(), 1000, 200, 800, 0);
        assertTrue(PayRollServiceImp.validateData(payRoll));
    }

    @Test
    public void testValidateData_Invalid() {
        PayRoll payRoll = new PayRoll(1, 0, null, null, -1000, -200, -800, 0);
        assertFalse(PayRollServiceImp.validateData(payRoll));
    }
}
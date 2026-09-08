package com.mycompany.part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login system;

    // This method runs before every test to create a fresh Login object
    @BeforeEach
    void setUp() {
        system = new Login();
    }

    // Tests a correctly formatted username
    @Test
    void testUsernameCorrectlyFormatted() {
        assertTrue(system.checkUserName("sip_3"));
        assertEquals("Username successfully captured.",
                     system.getUsernameCaptureMessage("sip_3"));
    }

    // Tests an incorrectly formatted username
    @Test
    void testUsernameIncorrectlyFormatted() {
        assertFalse(system.checkUserName("sipho!!!!"));
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                     system.getUsernameCaptureMessage("sipho!!!!"));
    }

    // Tests a password that meets the complexity rules
    @Test
    void testPasswordMeetsComplexity() {
        assertTrue(system.checkPasswordComplexity("S@f3Code!"));
        assertEquals("Password successfully captured.",
                     system.getPasswordCaptureMessage("S@f3Code!"));
    }

    // Tests a password that does not meet the complexity rules
    @Test
    void testPasswordDoesNotMeetComplexity() {
        assertFalse(system.checkPasswordComplexity("weakpass"));
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                     system.getPasswordCaptureMessage("weakpass"));
    }

    // Tests a correctly formatted cell phone number
    @Test
    void testCellPhoneCorrectlyFormatted() {
        assertTrue(system.checkCellPhoneNumber("+27834567890"));
        assertEquals("Cell number successfully captured.",
                     system.getCellPhoneCaptureMessage("+27834567890"));
    }

    // Tests an incorrectly formatted cell phone number
    @Test
    void testCellPhoneIncorrectlyFormatted() {
        assertFalse(system.checkCellPhoneNumber("0834567890"));
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
                     system.getCellPhoneCaptureMessage("0834567890"));
    }

    // Tests a successful login
    @Test
    void testLoginSuccessful() {
        system.registerUser("sip_3", "S@f3Code!", "+27834567890", "Sipho", "Dlamini");
        assertTrue(system.loginUser("sip_3", "S@f3Code!"));
        assertEquals("Welcome Sipho Dlamini, it is great to see you again.",
                     system.returnLoginStatus("sip_3", "S@f3Code!"));
    }

    // Tests a failed login
    @Test
    void testLoginFailed() {
        system.registerUser("sip_3", "S@f3Code!", "+27834567890", "Sipho", "Dlamini");
        assertFalse(system.loginUser("wrong", "wrong"));
        assertEquals("Username or password incorrect, please try again.",
                     system.returnLoginStatus("wrong", "wrong"));
    }
}
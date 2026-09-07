package com.mycompany.part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
    }

    @Test
    void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
        assertEquals("Username successfully captured.",
                     login.getUsernameCaptureMessage("kyl_1"));
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                     login.getUsernameCaptureMessage("kyle!!!!!!!"));
    }

    @Test
    void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertEquals("Password successfully captured.",
                     login.getPasswordCaptureMessage("Ch&&sec@ke99!"));
    }

    @Test
    void testPasswordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                     login.getPasswordCaptureMessage("password"));
    }

    @Test
    void testCellPhoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        assertEquals("Cell number successfully captured.",
                     login.getCellPhoneCaptureMessage("+27838968976"));
    }

    @Test
    void testCellPhoneIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
                     login.getCellPhoneCaptureMessage("08966553"));
    }

    @Test
    void testLoginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Welcome Kyle Smith, it is great to see you again.",
                     login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(login.loginUser("wrong", "wrong"));
        assertEquals("Username or password incorrect, please try again.",
                     login.returnLoginStatus("wrong", "wrong"));
    }
}
package com.mycompany.part1;

import java.util.regex.Pattern;

public class Login {

    // These variables store the registered user details
    private String storedUsername;
    private String storedPassword;
    private String storedCellPhone;
    private String firstName;
    private String lastName;

    // Checks if the username contains an underscore and is 5 characters or less
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    // Checks if the password meets the complexity requirements
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    // Checks if the cell phone number starts with + and is the correct length
    public boolean checkCellPhoneNumber(String cellPhone) {
        if (cellPhone == null) return false;
        return Pattern.matches("^\\+\\d{1,12}$", cellPhone) && cellPhone.length() <= 13;
    }

    // Registers the user if all details are valid
    public String registerUser(String username, String password, String cellPhone,
                               String firstName, String lastName) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        // Store the user details after successful validation
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellPhone = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;

        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
    }

    // Checks if the login details match the registered details
    public boolean loginUser(String username, String password) {
        if (storedUsername == null || storedPassword == null) return false;
        return storedUsername.equals(username) && storedPassword.equals(password);
    }

    // Returns a welcome message or an error message depending on login success
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Returns the correct message after checking the username
    public String getUsernameCaptureMessage(String username) {
        if (checkUserName(username)) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }

    // Returns the correct message after checking the password
    public String getPasswordCaptureMessage(String password) {
        if (checkPasswordComplexity(password)) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }

    // Returns the correct message after checking the cell phone number
    public String getCellPhoneCaptureMessage(String cell) {
        if (checkCellPhoneNumber(cell)) {
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
    }
}
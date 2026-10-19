package com.mycompany.part1;

/**
 * This class handles everything related to user registration and login.
 * It stores the user's details after registration and checks if they
 * can log in successfully before allowing them to send messages.
 */
public class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedCellPhone;
    private String firstName;
    private String lastName;
    private boolean isLoggedIn = false;

    /**
     * Checks if the username is correctly formatted.
     * Rules: must contain an underscore and be no longer than 5 characters.
     */
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    public String getUsernameCaptureMessage(String username) {
        if (checkUserName(username)) {
            return "Username successfully captured.";
        }
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }

    /**
     * Checks if the password meets the complexity rules.
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    public String getPasswordCaptureMessage(String password) {
        if (checkPasswordComplexity(password)) {
            return "Password successfully captured.";
        }
        return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    /**
     * Checks if the cell phone number is correctly formatted.
     * Must start with +27 followed by exactly 9 digits.
     */
    public boolean checkCellPhoneNumber(String cell) {
        if (cell == null) return false;
        return cell.matches("^\\+27\\d{9}$");
    }

    public String getCellPhoneCaptureMessage(String cell) {
        if (checkCellPhoneNumber(cell)) {
            return "Cell number successfully captured.";
        }
        return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    }

    /**
     * Registers a new user after all validations have passed.
     */
    public String registerUser(String username, String password, String cell,
                               String firstName, String lastName) {
        if (!checkUserName(username)) return getUsernameCaptureMessage(username);
        if (!checkPasswordComplexity(password)) return getPasswordCaptureMessage(password);
        if (!checkCellPhoneNumber(cell)) return getCellPhoneCaptureMessage(cell);

        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellPhone = cell;
        this.firstName = firstName;
        this.lastName = lastName;

        return "User has been registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        if (storedUsername == null || storedPassword == null) return false;

        boolean success = username.equals(storedUsername) && password.equals(storedPassword);
        isLoggedIn = success;
        return success;
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
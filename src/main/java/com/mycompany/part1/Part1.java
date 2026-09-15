package com.mycompany.part1;

import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== Registration & Login ===\n");

        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();
        // Loop until username is valid
        String username;
        while (true) {
            System.out.print("Enter username: ");
            username = sc.nextLine();
            System.out.println(login.getUsernameCaptureMessage(username));
            if (login.checkUserName(username)) {
                break;
            }
        }

        // Loop until password is valid
        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = sc.nextLine();

            System.out.println(login.getPasswordCaptureMessage(password));
            if (login.checkPasswordComplexity(password)) {
                break;
            }
        }

        // Loop until cell phone is valid
        String cell;
        while (true) {
            System.out.print("Enter South African cell phone number (e.g. +27838968976): ");
            cell = sc.nextLine();
            System.out.println(login.getCellPhoneCaptureMessage(cell));
            if (login.checkCellPhoneNumber(cell)) {
                break;
            }
        }

        // Register the user
        String regResult = login.registerUser(username, password, cell, firstName, lastName);
        System.out.println("\nRegistration result:\n" + regResult);

        // Login section
        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String loginUser = sc.nextLine();

        System.out.print("Enter password: ");
        String loginPass = sc.nextLine();

        System.out.println(login.returnLoginStatus(loginUser, loginPass));

        sc.close();
    }
}
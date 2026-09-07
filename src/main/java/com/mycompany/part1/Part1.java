package com.mycompany.part1;

import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();

        System.out.println("===  Registration & Login ===\n");

        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter South African cell phone number (e.g. +27838968976): ");
        String cell = sc.nextLine();

        System.out.println(login.getUsernameCaptureMessage(username));
        System.out.println(login.getPasswordCaptureMessage(password));
        System.out.println(login.getCellPhoneCaptureMessage(cell));

        String regResult = login.registerUser(username, password, cell, firstName, lastName);
        System.out.println("\nRegistration result:\n" + regResult);

        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String loginUser = sc.nextLine();

        System.out.print("Enter password: ");
        String loginPass = sc.nextLine();
        
        
        
        
        

        System.out.println(login.returnLoginStatus(loginUser, loginPass));

        sc.close();
    }
}
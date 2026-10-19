package com.mycompany.part1;

import java.util.Scanner;

/**
 * This is the main class that runs the whole QuickChat application.
 * It first registers and logs the user in (Part 1),
 * then allows them to send messages using a simple menu (Part 2).
 */
public class QuickChatApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();

        // ========== REGISTRATION SECTION ==========
        System.out.println("=== QuickChat Registration ===");
        System.out.print("First name: ");
        String first = sc.nextLine();
        System.out.print("Last name: ");
        String last = sc.nextLine();

        String username, password, cell;

        // Keep asking for username until it is valid
        while (true) {
            System.out.print("Username: ");
            username = sc.nextLine();
            System.out.println(login.getUsernameCaptureMessage(username));
            if (login.checkUserName(username)) break;
        }

        // Keep asking for password until it is valid
        while (true) {
            System.out.print("Password: ");
            password = sc.nextLine();
            System.out.println(login.getPasswordCaptureMessage(password));
            if (login.checkPasswordComplexity(password)) break;
        }

        // Keep asking for cell number until it is valid
        while (true) {
            System.out.print("Cell (+27...): ");
            cell = sc.nextLine();
            System.out.println(login.getCellPhoneCaptureMessage(cell));
            if (login.checkCellPhoneNumber(cell)) break;
        }

        System.out.println(login.registerUser(username, password, cell, first, last));

        // ========== LOGIN SECTION ==========
        System.out.println("\n=== Login ===");
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();
        System.out.println(login.returnLoginStatus(u, p));

        // Only continue if the user successfully logged in
        if (!login.isLoggedIn()) {
            System.out.println("You must be logged in to use QuickChat. Exiting.");
            return;
        }

        // ========== PART 2 - SENDING MESSAGES ==========
        System.out.println("\nWelcome to QuickChat.");

        System.out.print("How many messages would you like to send? ");
        int maxMessages = Integer.parseInt(sc.nextLine());

        int messagesCreated = 0;

        // Main menu loop - keeps running until user chooses Quit
        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Option: ");
            String choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println("Total messages sent: " + Message.returnTotalMessages());
                System.out.println("Goodbye!");
                break;
            }

            if (choice.equals("2")) {
                System.out.println("Coming Soon.");
                continue;
            }

            if (choice.equals("1")) {
                // Check if the user has already reached the limit
                if (messagesCreated >= maxMessages) {
                    System.out.println("You have reached the maximum number of messages allowed.");
                    continue;
                }

                System.out.print("Recipient cell number (+27...): ");
                String recipient = sc.nextLine();

                System.out.print("Enter your message (max 250 characters): ");
                String text = sc.nextLine();

                // Validate message length
                if (text.length() > 250) {
                    System.out.println("Please enter a message of less than 250 characters.");
                    continue;
                }

                // Create the message object
                Message msg = new Message(recipient, text);

                // Show the user all the details before they decide
                System.out.println("\nMessage ID: " + msg.getMessageId());
                System.out.println("Message Hash: " + msg.getMessageHash());
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getMessageText());

                System.out.println("\nWhat would you like to do?");
                System.out.println("1) Send Message");
                System.out.println("2) Disregard Message");
                System.out.println("3) Store Message to send later");
                System.out.print("Choice: ");
                String action = sc.nextLine();

                System.out.println(msg.sentMessage(action));
                messagesCreated++;
            } else {
                System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }

        sc.close();
    }
}
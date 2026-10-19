package com.mycompany.part1;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a single message that a user wants to send.
 * It also keeps a list of all messages that have been successfully
 * sent during the current run of the program.
 */
public class Message {

    // Details of one individual message
    private String messageId;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // This list stores every message that was successfully sent
    private static ArrayList<Message> allMessages = new ArrayList<>();
    
    // Keeps track of how many messages have been created
    private static int totalMessagesSent = 0;

    /**
     * Constructor - creates a new message.
     * Automatically generates a unique ID and calculates the hash.
     */
    public Message(String recipient, String messageText) {
        this.messageId = generateMessageId();
        this.messageNumber = ++totalMessagesSent;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    /**
     * Checks that the Message ID is not longer than 10 characters.
     */
    public boolean checkMessageID() {
        return messageId != null && messageId.length() <= 10;
    }

    /**
     * Checks if the recipient's cell number is correctly formatted.
     * Must start with +27 and contain 9 more digits.
     */
    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    /**
     * Creates the Message Hash required by the assignment.
     * Format example: 00:1:HITONIGHT
     */
    public String createMessageHash() {
        String firstTwo = messageId.substring(0, 2);

        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

        return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    /**
     * Lets the user choose what to do with the message:
     * 1 = Send, 2 = Disregard, 3 = Store
     */
    public String sentMessage(String choice) {
        if (choice.equals("1") || choice.equalsIgnoreCase("send")) {
            allMessages.add(this);
            return "Message successfully sent.";
        } else if (choice.equals("2") || choice.equalsIgnoreCase("disregard") || choice.equals("0")) {
            totalMessagesSent--;   // undo the counter increase
            return "Press 0 to delete message.";
        } else if (choice.equals("3") || choice.equalsIgnoreCase("store")) {
            allMessages.add(this);
            return "Message successfully stored.";
        }
        return "Invalid option.";
    }

    /**
     * Returns a readable list of all messages that have been sent.
     */
    public static String printMessages() {
        if (allMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }

        StringBuilder sb = new StringBuilder();
        for (Message m : allMessages) {
            sb.append("Message ID: ").append(m.messageId).append("\n");
            sb.append("Message Hash: ").append(m.messageHash).append("\n");
            sb.append("Recipient: ").append(m.recipient).append("\n");
            sb.append("Message: ").append(m.messageText).append("\n");
            sb.append("-------------------------\n");
        }
        return sb.toString();
    }

    /**
     * Returns the total number of messages that were successfully sent.
     */
    public static int returnTotalMessages() {
        return allMessages.size();
    }

    /**
     * Helper method that generates a random 10-digit Message ID.
     */
    private String generateMessageId() {
        Random rnd = new Random();
        long number = 1000000000L + (long) (rnd.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    // Simple getters so other classes can read the message details
    public String getMessageId()   { return messageId; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient()   { return recipient; }
    public String getMessageText() { return messageText; }
}
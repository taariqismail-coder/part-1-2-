package com.mycompany.part1;

import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;

/**
 * This class represents a single message that a user wants to send.
 * It also keeps a list of all messages that have been successfully
 * sent during the current run of the program and can save them to JSON.
 */
public class Message {

    private String messageId;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    private static ArrayList<Message> allMessages = new ArrayList<>();
    private static int totalMessagesSent = 0;

    public Message(String recipient, String messageText) {
        this.messageId = generateMessageId();
        this.messageNumber = ++totalMessagesSent;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    public boolean checkMessageID() {
        return messageId != null && messageId.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String createMessageHash() {
        String firstTwo = messageId.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public String sentMessage(String choice) {
        if (choice.equals("1") || choice.equalsIgnoreCase("send")) {
            allMessages.add(this);
            return "Message successfully sent.";
        } else if (choice.equals("2") || choice.equalsIgnoreCase("disregard") || choice.equals("0")) {
            totalMessagesSent--;
            return "Press 0 to delete message.";
        } else if (choice.equals("3") || choice.equalsIgnoreCase("store")) {
            allMessages.add(this);
            storeMessagesToJSON();
            return "Message successfully stored.";
        }
        return "Invalid option.";
    }

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

    public static int returnTotalMessages() {
        return allMessages.size();
    }

    /**
     * Saves all messages into a JSON file called messages.json
     */
    public static void storeMessagesToJSON() {
        try {
            FileWriter file = new FileWriter("messages.json");
            file.write("[\n");

            for (int i = 0; i < allMessages.size(); i++) {
                Message m = allMessages.get(i);

                file.write("  {\n");
                file.write("    \"messageId\": \"" + m.messageId + "\",\n");
                file.write("    \"messageHash\": \"" + m.messageHash + "\",\n");
                file.write("    \"recipient\": \"" + m.recipient + "\",\n");
                file.write("    \"message\": \"" + m.messageText.replace("\"", "'") + "\"\n");
                file.write("  }");

                if (i < allMessages.size() - 1) {
                    file.write(",\n");
                } else {
                    file.write("\n");
                }
            }

            file.write("]");
            file.close();
            System.out.println("Messages successfully stored in messages.json");

        } catch (Exception e) {
            System.out.println("Error while saving messages to JSON: " + e.getMessage());
        }
    }

    private String generateMessageId() {
        Random rnd = new Random();
        long number = 1000000000L + (long) (rnd.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
}
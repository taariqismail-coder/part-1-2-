package com.mycompany.part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Message class (Part 2 requirements).
 */
public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message m = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(m.getMessageText().length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {
        String longText = "a".repeat(251);
        Message m = new Message("+27718693002", longText);
        assertTrue(m.getMessageText().length() > 250);
    }

    @Test
    public void testRecipientSuccess() {
        Message m = new Message("+27718693002", "Hello");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        Message m = new Message("08575975889", "Hello");
        assertTrue(m.checkRecipientCell().contains("incorrectly formatted"));
    }

    @Test
    public void testMessageHash() {
        Message m = new Message("+27718693002", "Hi thanks");
        String hash = m.getMessageHash();
        
        // Hash must contain colons and be uppercase
        assertTrue(hash.contains(":"));
        assertEquals(hash, hash.toUpperCase());
    }

    @Test
    public void testSentMessageSend() {
        Message m = new Message("+27718693002", "Test message");
        assertEquals("Message successfully sent.", m.sentMessage("1"));
    }

    @Test
    public void testSentMessageDisregard() {
        Message m = new Message("+27718693002", "Test message");
        assertEquals("Press 0 to delete message.", m.sentMessage("2"));
    }

    @Test
    public void testSentMessageStore() {
        Message m = new Message("+27718693002", "Test message");
        assertEquals("Message successfully stored.", m.sentMessage("3"));
    }

    @Test
    public void testMessageIDLength() {
        Message m = new Message("+27718693002", "Hello");
        assertTrue(m.checkMessageID());
    }
}
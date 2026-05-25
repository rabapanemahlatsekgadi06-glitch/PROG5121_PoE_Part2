
package prog5121_poe_part2;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class MessageTest {
    
   private Message msg1;
   private Message msg2;
   
    @Before
    public void setUp() {
        msg1 = new Message("+27718693002" , "Hi Mike, can you join us for dinner tonight?");
        msg2 = new Message("0887597889", "Hi Keegan, did you recieve the payment?");
    }
   
@Test
    public void testRecipientCellSuccess() {
        String result = msg1.checkRecipientCell();
        System.out.println("Input: +27718693002");
        System.out.println("Expected: Cell phone number successfully captured.");
        System.out.println("Output: " + result);
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientCellFailure() {
        String result = msg2.checkRecipientCell();
        System.out.println("Input: 08575975889");
        System.out.println("Expected: Cell phone number is incorrectly formatted...");
        System.out.println("Output: " + result);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHashCorrect() {
        String hash = msg1.checkMessageHash();
        System.out.println("Input: Hi Mike, can you join us for dinner tonight?");
        System.out.println("Expected hash to contain: HITONIGHT");
        System.out.println("Output: " + hash);
        assertTrue("Hash should contain HITONIGHT. Actual: " + hash, hash.contains("HITONIGHT"));
    }

    @Test
    public void testMessageLengthSuccess() {
        String result = msg1.checkMessageLength();
        System.out.println("Input: Hi Mike, can you join us for dinner tonight?");
        System.out.println("Expected: Message ready to send.");
        System.out.println("Output: " + result);
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        Message longMsg = new Message("+27718693002", "A".repeat(260));
        String result = longMsg.checkMessageLength();
        System.out.println("Input: 260 character message");
        System.out.println("Expected: Message exceeds 250 characters by...");
        System.out.println("Output: " + result);
        assertTrue(result.startsWith("Message exceeds 250 characters by"));
    }

    @Test
    public void testMessageIDCreated() {
        String id = msg1.getMessageID();
        System.out.println("Generated Message ID: " + id);
        System.out.println("ID length valid (<=10): " + msg1.checkMessageID());
        assertNotNull(id);
        assertTrue(msg1.checkMessageID());
    }

    @Test
    public void testSentMessageSend() {
        String result = msg1.sentMessage(1);
        System.out.println("Input: Option 1 - Send");
        System.out.println("Expected: Message successfully sent.");
        System.out.println("Output: " + result);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testSentMessageDisregard() {
        String result = msg2.sentMessage(2);
        System.out.println("Input: Option 2 - Disregard");
        System.out.println("Expected: Press 0 to delete the message.");
        System.out.println("Output: " + result);
        assertEquals("Press 0 to delete the message.", result);
    }

    @Test
    public void testSentMessageStore() {
        String result = msg1.sentMessage(3);
        System.out.println("Input: Option 3 - Store");
        System.out.println("Expected: Message successfully stored.");
        System.out.println("Output: " + result);
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testReturnTotalMessages() {
        int before = Message.returnTotalMessage();
        msg1.sentMessage(1);
        int after = Message.returnTotalMessage();
        System.out.println("Messages before: " + before);
        System.out.println("Messages after sending: " + after);
        System.out.println("Total messages sent: " + after);
        assertEquals(before + 1, after);
    }
}

package prog5121_poe_part2;

import java.util.ArrayList;
import java.util.Random;

public class Message {
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private static ArrayList<String> sentMessages = new ArrayList<>();
    private static int totalMessagesSent = 0;
    
    public Message(String recipientCell, String messageText){
     this.messageID = generateMessageID();
     this.recipientCell = recipientCell;
     this.messageText = messageText;
     this.messageHash = checkMessageHash();
    }
    
    private String generateMessageID(){
        Random rand = new Random();
        long id = (long)(rand.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(id);
    }
    
    public boolean checkMessageID(){
      return messageID.length() <= 10;
    }
    
    public String checkRecipientCell(){
     if(recipientCell.matches("\\+[0-9]{10,12}")){
       return "Cell phone number successfuly captured.";
     }else{
         return "Cell phone number does not contain an international code. Please correct the number and try again.";
     }   
    }
    
    public String checkMessageHash(){
      String[] words = messageText.trim().split("\\s+");
      String firstWord = words[0].replaceAll("[a-zA-Z0-9]", "");
      String lastWord = words[words.length - 1].replaceAll("[a-zA-Z0-9]", "");
      String hash = messageID.substring(0,2) + ":" + totalMessagesSent + ":" + firstWord + lastWord;
      this.messageHash = hash.toUpperCase();
      return this.messageHash;
    }
    
    
}


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
    
    public String checkMessageLength(){
     if(messageText.length()<= 250){
       return "Message ready to send.";  
     }else{
        int excess = messageText.length() - 250;
        return "Message exceeds 250 characters by" + excess + "; please reduce the size.";
     }  
    }

    
    public String sentMessage(int choice){
      switch(choice){
          case 1:
           totalMessagesSent++;
           sentMessages.add(formatMessageDetails());
           return "Message successfully sent.";
          case 2:
            return "Press 0 to delete the message.";
          case 3:
              storeMessage();
             return "Message successfully stored.";
          default:
              return "Invalid option";
    }
}

public void storeMessage(){
    try{
        java.io.FileWriter fw = new java.io.FileWriter("stored_messages.json", true);
        fw.write("{\n");
        fw.write("\" messageID\":\"" + messageID + "\",\n");
        fw.write("\" messageHash\" : \"" + messageHash + "\",\n");
        fw.write("\"recipient\" : \"" + recipientCell + "\",n");
        fw.write("\"message\" : \"" + messageText + "\"\n");
        fw.write("}\n");
        fw.close();
        System.out.println("Message successfully stored to JSON.");
    }catch (Exception e){
       System.out.println("Error storing message: " + e.getMessage());
    }
}

public static String printMessages(){
    if(sentMessages.isEmpty()){
        return"No messages sent yet.";
    }
    StringBuilder sb = new StringBuilder();
    for(String msg : sentMessages){
        sb.append(msg).append("\n\n");
    }
    return sb.toString().trim();
}

public static int returnTotalMessage(){
    return totalMessagesSent;
}

public String formatMessageDetails(){
    return "Message ID: " +messageID +
            "\nMessage Hash: " + messageHash +
             "\nRecipient: " + recipientCell +
            "\nMessage: " + messageText;
}

public String getMessageID(){return messageID;}
public String getMessageHash(){return messageHash;}
public String getRecipientCell(){return recipientCell;}
public String getMessageText(){return messageText;}
}

package prog5121_poe_part2;

import java.util.Scanner;

public class PROG5121_PoE_Part2 {

   
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
  System.out.println("Welcome to QuickChat");
   System.out.print("Enter username: ");
   String username = scanner.nextLine();
  System.out.print("Enter password: ");
  String password = scanner.nextLine();
  
  if(!username.equals("Mahl8") || !password.equals("mahlats_eRab309")){
    System.out.println("Login failed. Exiting.");
    return;
  }
  
  System.out.println("\nWelcome to QuickChat.");
  
  boolean running = true;
  while(running){
      System.out.println("\n--- Main Menu ---");
      System.out.println("1) Send Messages");
      System.out.println("2) Show recently sent messages");
      System.out.println("3) Quit");
      System.out.println("Choose an option: ");
      
      int menuChoice;
  try{
      menuChoice = Integer.parseInt(scanner.nextLine().trim());
  }catch(NumberFormatException e){
      System.out.println("Invalid input. Please enter 1.2. or 3.");
      continue;
  }
   switch(menuChoice){
       case 1:
           sendMessagesFlow(scanner);
           break;
       case 2:
           System.out.println("coming Soon.");
           break;
       case 3:
           running = false;
           System.out.println("\n--- Session Summary ---");
           System.out.println("Total messages sent: " + Message.returnTotalMessage());
           System.out.println("Goodbye");
           break;
       default:
           System.out.println("Invalid option. Please choose 1,2, 0r 3.");
   } 
  }
  }

private static void sendMessagesFlow(Scanner scanner){
   System.out.print("\nHow many messages do you want to send?");
   int numMessages;
   try{
       numMessages = Integer.parseInt(scanner.nextLine().trim());    
   }catch(NumberFormatException e){
     System.out.println("Invalid number. Return to menu.");
     return;
   }
   
   for(int i=0; i < numMessages;i++){
       System.out.println("\n--- Message" + (i + 1) + "of" + numMessages + "---");
       
    String recipientCell = "";
    while(true){
        System.out.print("Enter recipient cell number (e.g. +27...): ");
        recipientCell = scanner.nextLine().trim();
        Message tempMsg = new Message(recipientCell, "placeholder");
        String cellCheck = tempMsg.checkRecipientCell();
        System.out.println(cellCheck);
       if(cellCheck.equals("Cell phone number successfully captured.")){
           break;
       } 
    }
    
    String messageText;
    while(true){
        System.out.print("Enter your message(max 250 characters): ");
        messageText = scanner.nextLine();
        Message tempMsg = new Message(recipientCell, messageText);
        String lengthCheck = tempMsg.checkMessageLength();
        if(lengthCheck.equals("Mrssage ready to send.")){
            break;
        }else{
            System.out.println(lengthCheck);
        }
    }
  
   Message message = new Message(recipientCell, messageText);
   System.out.println("Message ID generated: " + message.getMessageID());
   System.out.println("Message Hash: " + message.getMessageHash());
   
       System.out.println("\n1) Send Message");
       System.out.println("2) Disregard Message");
       System.out.println("3) Store Message");
       System.out.print("Choose: ");
       
       int choice;
       try{
       choice = Integer.parseInt(scanner.nextLine().trim());
   }catch(NumberFormatException e){
       choice =2;
   }

   
   System.out.println(message.sentMessage(choice));
 
   if(choice == 1){
       System.out.println("\n" + message.formatMessageDetails());
   }
   }
   
    System.out.println("\nTotal messages sent: " + Message.returnTotalMessage());
}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.nkala.ntokozo.prog.part2;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


public class Message{
   String id;
   int count;
   String recipient;
   String content;
   String hash;
   
   private static List<Message> sentMessages = new ArrayList<>();
   private static int totalMessagesSent = 0;
   private static Scanner scanner = new Scanner(System.in);
   
   public Message(String id, int count, String recipient, String content, String hash){
       this.id = id;
       this.count = count;
       this.recipient = recipient;
       this.content = content;
       this.hash = hash;
   }  
       public static void show(){
           System.out.println("=======Welcome to QuickChat.======");
           boolean running = true;
           
           while(running){
              System.out.println("\n1. Send Message");
              System.out.println("2. Show recently sent messages");
              System.out.println("3. Quit");
              System.out.println("Choose an option: ");
              String choice = scanner.nextLine().trim();
              
              switch(choice){
                  case "1":
                      sendMessages();
                      break;
                  case "2": 
                      System.out.println("Coming soon");
                      break;
                  case "3":
                      System.out.println("Goodbye!");
                      running = false;
                      break;
                  default:
                      System.out.println("Error: Invalid option. Please enter 1, 2, or 3");
              }
           }
       }
       
       //asks user how many messages they want to read
       //loops and builds each one
       private static void sendMessages(){
           System.out.println("\nHow many messages do you want to send?");
           String input = scanner.nextLine().trim();
           
          int count;
          try{
              count = Integer.parseInt(input);
              if(count <= 0){
                  System.out.println("Error: Please enter a number greater than zero");
                  return;
              }
          }
              catch(NumberFormatException e){
                      System.out.println("Error: Invalid input. Please enter a number");
                      return;
                      }

          for (int i = 1; i <= count; i++){ 
            System.out.println("\n--Message " + i + "of" + count + "--"); 
            Message msg = messageValidation(i); 
            msg.SentMessage(); 
        }  
        System.out.println("\nTotal messages sent: " + returnTotalMessagess()); 
        System.out.println(printMessages()); 
    } 
 
    // Ensures that characters are 10 or less. 
    public boolean checkMessageID(){
       return this.id != null && this.id.length() <= 10;
    }
     
    // ensures that international code contains ('+') and is 10 or less numbers. 
    public String checkRecipientCell(){ 
        if (this.recipient == null){ 
            return"Cell number cannot be empty."; 
        } 
 
        String digits = this.recipient.substring(1); 
        if (digits.length() > 10){ 
            return"Cell number must be no more than ten characters long."; 
        } 
        return"Cell number successfully captured"; 
    } 
    
   //formats = first two digits of ID, message count, firstord and lastword are caps
    public String createMessageHash(){ 
        String[] words = this.content.trim().split("\\s+"); 
        String firstWord = words[0]; 
        String lastWord = words[words.length - 1]; 
        String generatedHash =(this.id.substring(0, 2) + ":" + this.count + ":" + firstWord + lastWord).toUpperCase(); 
        this.hash = generatedHash; 
        return generatedHash; 
    } 
 
    // Allows the user to (1)send,(2) delete/disregard, or (3)store the message for later. 
    public String SentMessage(){ 
        System.out.println("\nChoose an action for this message:"); 
        System.out.println("1. Send Message"); 
        System.out.println("2. Disregard Message"); 
        System.out.println("3. Store Message to send later"); 
        System.out.print("Your choice: "); 
        String choice = scanner.nextLine().trim(); 
 
        switch(choice){ 
            case "1": 
                sentMessages.add(this); 
                totalMessagesSent++; 
                System.out.println("Message successfully sent"); 
                System.out.println("\n--Message Details--"); 
                System.out.println("Message ID:" + this.id); 
                System.out.println("Message Hash:" + this.hash); 
                System.out.println("Recipient:" + this.recipient); 
                System.out.println("Message:"+ this.content); 
                return"Message successfully sent"; 
 
            case "2": 
                System.out.println("Press 0 to delete the message"); 
                String confirm = scanner.nextLine().trim(); 
                if(confirm.equals("0")){ 
                    System.out.println("Message deleted."); 
                    return"Message disregarded"; 
                } else{ 
                    System.out.println("Deletion cancelled."); 
                    return"Message disregard cancelled"; 
                } 
 
            case "3": 
                sentMessages.add(this); 
                storeMessage(); 
                System.out.println("Message successfully stored"); 
                return"Message is successfully stored"; 
 
            default: 
                System.out.println("Invalid choice. Message disregarded"); 
                return"Invalid choice message disregarded"; 
        } 
    } 
 
    // Returns all the messages sent while the program is running. 
    public static String printMessages(){ 
        if(sentMessages.isEmpty()){ 
            return"No messages have been sent yet."; 
        } 
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < sentMessages.size(); i++) {
            Message m = sentMessages.get(i);
            sb.append("Message ID: " + m.id + "\n");
            sb.append("Message Hash: " + m.hash + "\n");
            sb.append("Recipient: " + m.recipient + "\n");
            sb.append("Message: " + m.content + "\n");
            sb.append("--------------------------\n");
        } 
        return sb.toString(); 
    } 
 
    // Returns the total number of messages sent. 
    public static int returnTotalMessagess(){ 
        return totalMessagesSent; 
    } 
 
    // saves all the messages to a JSON file. 
    public void storeMessage(){ 
        StringBuilder sb = new StringBuilder("[\n"); 
        for (int i = 0; i <sentMessages.size(); i++){ 
            Message m = sentMessages.get(i); 
            sb.append("{\n"); 
            sb.append("\"message_id\": \"" + m.id + "\",\n"); 
            sb.append("\"recipient\": \"" + m.recipient + "\",\n"); 
            sb.append("\"count\":" + m.count + "\",\n"); 
            sb.append("\"content\": \"" + m.content.replace("\"", "\\\"") + "\",\n"); 
            sb.append("\"hash\": \"" + m.hash + "\"\n"); 
            sb.append("  }"); 
            if (i < sentMessages.size() - 1){
                sb.append(",");
            } 
            sb.append("\n"); 
        } 
        sb.append("]"); 
 
        try(FileWriter file = new FileWriter("stored_messages.json")){ 
            file.write(sb.toString()); 
            file.close(); 
            System.out.println("Messages written to stored_messages.json"); 
        } catch(IOException e){ 
            System.out.println("Error: Could not save JSON: " + e.getMessage()); 
        } 
    } 
    
     //prompts user for their recipient and message information
    //validates the information before building and returning a full message
    public static Message messageValidation(int messageNumber){ 
        String recipient; 
        while(true){ 
            System.out.print("Enter recipient phone number (e.g. +27821234567): "); 
            recipient = scanner.nextLine().trim(); 
            Message temp = new Message("0000000000", messageNumber, recipient, "test", ""); 
            String cellCheck = temp.checkRecipientCell(); 
            if (cellCheck.equals("Cell number successfully captured.")) { 
                break; 
            }
                    System.out.println("Error: + cellCheck");
        }
        
        
        //Message content 
        String messageText; 
        while(true){ 
            System.out.println("Enter your message (max 250 characters): "); 
             messageText = scanner.nextLine(); 
            
            if(messageText.isEmpty()){ 
                System.out.println("Error: Message cannot be empty. Please try again."); 
               continue; 
            } 
            if (messageText.length() > 250){ 
                System.out.println("Error: Please enter a message of less than 250 characters"); 
                continue; 
            }
            else{
                System.out.println("Message entered");
            }
            break; 
        } 
        
        //generates random 10 digit message ID 
        String msgID = String.format("%010d", new Random().nextInt(1_000_000_000)); 
 
        //Build, hash, and validate 
        Message msg = new Message(msgID, messageNumber, recipient, messageText, ""); 
        msg.createMessageHash(); 
 
        if (!msg.checkMessageID()){ 
            msgID = String.format("%010d", new Random().nextInt(1_000_000_000)); 
            msg.id = msgID; 
        }
        return msg; 
    } 
}            
          
       
   


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.nkala.ntokozo.prog.part2;
import java.util.*; 

public class Main{
 
    public static void main(String[] args){
 
        Scanner scanner = new Scanner(System.in);
        Login userLogin = new Login();
        boolean registered = false;
 
        System.out.println("========================================");
        System.out.println("|       Welcome to QuickChat App        |");
        System.out.println("========================================");
 
        while(true){
            System.out.println("\nMain Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
 
            if(choice.equals("1")) {
 
                System.out.println("\n====== ACCOUNT REGISTRATION =====");
 
                System.out.println("Enter your First Name: ");
                String firstName = scanner.nextLine();
 
                System.out.println("Enter your Last Name: ");
                String lastName = scanner.nextLine();
 
                System.out.println("Enter a Username: ");
                String username = scanner.nextLine();
 
                System.out.println("Enter a Password: ");
                String password = scanner.nextLine();
 
                System.out.println("Enter your Cellphone Number: ");
                String phoneNumber = scanner.nextLine();
 
             String registerResult = userLogin.registerUser(firstName, lastName, username, password, phoneNumber);
                System.out.println(registerResult);
 
                if (userLogin.checkUserName(username) &&
                    userLogin.checkPasswordComplexity(password) &&
                    userLogin.checkCellPhoneNumber(phoneNumber)) {
                    registered = true;
                }
 
            } else if (choice.equals("2")) {
                if (!registered) {
                    System.out.println("Please register first before logging in.");
 
                } else {
                    System.out.println("\n====ACCOUNT LOGIN====");
 
                    System.out.println("Enter your Username: ");
                    String loginUsername = scanner.nextLine();
 
                    System.out.println("Enter your Password: ");
                    String loginPassword = scanner.nextLine();
 
                    String loginResult = userLogin.returnLoginStatus(loginUsername, loginPassword);
                    System.out.println(loginResult);
                    if (userLogin.loginUser(loginUsername, loginPassword)) {
                        Message.show();
                    }
                }
 
            } else if (choice.equals("3")) {
                System.out.println("Exiting. Goodbye!");
                scanner.close(); 
                return;  
 
            } else {
                System.out.println("Error: Invalid option. Please enter 1, 2, or 3.");
            }
        }
    }
}

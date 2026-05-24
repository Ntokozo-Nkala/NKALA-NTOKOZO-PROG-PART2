/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nkala.ntokozo.prog.part2;
import java.util.Scanner;

public class Login{
    private String username;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    
    
    //prompts the user for inputs/personal imformation
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    Login userLogin = new Login();
    System.out.println("====== ACCOUNT REGISTRATION =====");
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
    
    System.out.println(userLogin.registerUser(firstName, lastName, username, password, phoneNumber));
    if(userLogin.username == null){
        System.out.println("Registration failed. please try again");
         scanner.close();
         return;
    }
    
    System.out.println("==== ACCOUNT LOGIN ====");
    System.out.println("Enter your Username: ");
    username = scanner.nextLine();
    System.out.println("Enter your Password: ");
    password = scanner.nextLine();
    System.out.println(userLogin.returnLoginStatus(username, password));
    scanner.close();
}    
    
    
    //this method checks if username contains an underscore and is equal or less than 5 letters
    public boolean checkUserName(String username){
    return username.contains("_") && username.length() <=5;
}
    //method checks for a capital letter, a number and a special character
    public boolean checkPasswordComplexity(String password){
       return password.length() >= 8 &&
        password.matches(".*[A-Z].*")&&
        password.matches(".*[0-9].*") &&
        password.matches(".*[*!@$%^&()#].*");
    }
   
    //regex from: https://www.geeksforgeeks.org/java/java-program-to-check-for-a-valid-mobile-number/
    //method returns a phone number that contains an international country code
    public boolean checkCellPhoneNumber(String phoneNumber){
      String phoneNumberRegex = "^\\+{1,3}[0-9]{7,12}$";
            return phoneNumber.matches(phoneNumberRegex);
    }
    
    //method registers user if all conditions are met, if any of the conditions are not met it fails
    public String registerUser(String firstName, String lastName, String username, String password, String phoneNumber){
    if(!checkUserName(username)){
        return "Username is not correctly formatted; please ensure that your username contains an underscoore and is no more than five characters in length.";
    }
    else if(!checkPasswordComplexity(password)){
        return"Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }
    else if(!checkCellPhoneNumber(phoneNumber)){
        return"Cell phone number incorrectly formatted or does not contain international code.";
    }
    else{
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        return"Your Account is successfully registered! Welcome, " + firstName + ".";
    }
    }
    
   //this method verifies the users information
    public boolean loginUser(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
        }
    //method verifies the user's aithentication status
    public String returnLoginStatus(String username, String password){
        if(loginUser(username, password)){
            return"Welcome " + firstName + "," + lastName + " It is nice to see you again.";
        }
        else{
            return"Username or password incorrect. please try again.";
        }
    }
    //these make the private variables accessable
    public void userCredentials(String username, String password, String phoneNumber, String firstName, String lastName){
     this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}



    

    


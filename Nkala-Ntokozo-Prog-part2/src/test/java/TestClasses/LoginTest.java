/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestClasses;

import com.mycompany.nkala.ntokozo.prog.part2.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    @Test
    public void testCheckUserNameCorrectlyFormatted(){
    Login login = new Login();
    String expected = "ntok_";
    String Actual = login.registerUser("ntokozo", "nkala", "ntok_", "Nt&&for@ye22!", "+27810009346");
    assertEquals(expected,Actual,"Your Account is successfully registered! Welcome, ntokozo.");
    }
    
    @Test
    public void testCheckUserNameCorrectlyButFail() {
    Login login = new Login();
    String expected = "ntokozo!!!!!!!";
    String Actual = login.registerUser("ntokozo", "nkala", "ntok_", "Nt&&for@ye22!", "+27810009346");
    assertNotEquals(expected,Actual,"Your Account is successfully registered! Welcome, ntokozo.");
    }
    
    @Test
    public void testCheckUsePasswordComplexity(){
    Login login = new Login();
    String expected = "Nt&&for@ye22!";
    String Actual = login.registerUser("ntokozo", "nkala", "ntok_", "Nt&&for@ye22!", "+27810009346");
    assertEquals(expected,Actual,"Your Account is successfully registered! Welcome, ntokozo.");
    }
    
    @Test
    public void testCheckUsePasswordButFail(){
    Login login = new Login();
    String expected = "password";
    String Actual = login.registerUser("ntokozo", "nkala", "ntok_", "Nt&&for@ye22!", "+27810009346");
    assertNotEquals(expected,Actual,"Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
    }
    
    @Test
    public void testCheckCellPhoneNumber(){
    Login login = new Login();
    String expected = "+27810009346";
    String Actual = login.registerUser("ntokozo", "nkala", "ntok_", "Nt&&for@ye22!", "+27810009346");
    assertEquals(expected,Actual,"Your Account is successfully registered! Welcome, ntokozo.");
    }
    
    @Test
    public void testCheckCellPhoneNumberButFail(){
    Login login = new Login();
    String expected = "08966553";
    String Actual = login.registerUser("ntokozo", "nkala", "ntok_", "Nt&&for@ye22!", "+27810009346");
    assertNotEquals(expected,Actual,"Cell phone number incorrectly formatted or does not contains international code.");
    }
    
   @Test
   public void testreturnLoginStatus(){
   Login login = new Login();
   String expected = "ntok_, Nt&&for@ye22!";
   String Actual = login.returnLoginStatus("ntok_", "Nt&&for@ye22!");
   assertEquals(expected, Actual,"Welcome ntokozo,nkala. It is nice to see you again.");
   }
    
    @Test
   public void testreturnLoginStatusButFail(){
   Login login = new Login();
   String expected = "ntokozo!!!!!!!, password";
   String Actual = login.returnLoginStatus("ntok_", "Nt&&for@ye22!");
   assertNotEquals(expected, Actual,"Username or password incorrect. please try again.");
   }
   
   @Test
   public void testloginUser(){
       Login login = new Login();
       login.registerUser("ntokozo", "nkala", "ntok_", "Nt&&for@ye22!", "+27810009346");
        boolean actualResult = login.loginUser("ntok_", "Nt&&for@ye22!");
        assertTrue(actualResult);
   }
   
   @Test
   public void testloginUserButFail(){
       Login login = new Login();
        login.registerUser("ntokozo", "nkala", "ntok_", "Nt&&for@ye22!", "+27810009346");
        boolean actualResult = login.loginUser("ntokozo!!!!!!!", "password");
        assertFalse(actualResult);
   }
   

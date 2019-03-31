/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


/**
 *
 * @author Test
 */
public class Register implements Serializable{
    
    
      
    
    
  //  public void 
    
    
    public void landLordRegistration() throws IOException, ClassNotFoundException{
        Scanner input = new Scanner(System.in);
        LandLord landLord = new LandLord();
        String pass;
        String confirmPass;
        System.out.println("fill out the current information");
        
        System.out.println("Enter your first name");
         landLord.setFirstName(input.nextLine());
        
        System.out.println("Enter your Last name");
        landLord.setLastName(input.nextLine());
        
        
        System.out.println("enter your gender");
        landLord.setGender(input.nextLine());
        
                System.out.println("Enter Email Address");
       landLord.setAddress(input.nextLine());

        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";//pattern
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);//compile emailRegex into pattern
        Matcher matcher = emailPattern.matcher( landLord.getAddress());//match user input
        matcher.find();//find the next subsequence of the input sequence that matches the pattern.

        System.out.println("Enter Telephone Number");
        landLord.setTelephone(input.nextLine());

        String telNoRegex = "(?:\\d{3}-){2}\\d{4}";//pattern
        Pattern telPattern = Pattern.compile(telNoRegex, Pattern.CASE_INSENSITIVE);//compile telRegex into pattern
        matcher = telPattern.matcher(landLord.getTelephone());
        matcher.find();//find the next subsequence of the input sequence that matches the pattern.   
      
        
        System.out.println("Enter your account Number");
        landLord.setAcountNumber(input.nextLine());
        
               
        System.out.println("Enter your Id number");
        landLord.setID(input.nextLine());
       
        boolean b = true;
        while(b == true){
        System.out.println("enter your your password to use");
        pass =input.nextLine();
        
       System.out.println("Confirm password");
        confirmPass = input.nextLine();
        
        if(pass.equals(confirmPass)){
              
        landLord.setPassword(confirmPass);
        b = false;
        }else {
            System.out.println("Password enterd did not Match");
        }
        
      
        }


save(landLord);



 
    }
    
    
    public void save(LandLord landLordList) throws FileNotFoundException, IOException,EOFException, ClassNotFoundException{
 try{
  


Genertics G  = new Genertics(); 

ArrayList<LandLord> Write = new ArrayList<>();
Write = (ArrayList<LandLord>)G.getArrayObject(FileInterface.LAND_LORDFILE);

if(Write== null){
Write = new ArrayList<>();
Write.add(landLordList);
}else{
   LandLord c = Write.get(0);
     System.out.println(c.getFirstName() + c.getLastName());
    Write.add(landLordList);
}

G.SaveArrayObject(Write, FileInterface.LAND_LORDFILE);


   JOptionPane.showMessageDialog(null,"Information Saved Succesfuly....", "Save",0 );
	  Iterator<LandLord> pl = Write.iterator();
        while(pl.hasNext()){
            
            LandLord m = pl.next();
           System.out.println("From file: " + m.getFirstName()+" " + m.getID()+ ", " + m.getPassword()+ m.getGender());

           
            
        }


 }catch(IndexOutOfBoundsException x ){
     System.out.println(x.fillInStackTrace() + " fail");
 }

    }


  
    
    
    
    
}

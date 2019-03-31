/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

/** 
 *
 * @author Test
 */
abstract public class Person implements ContactDetails, Serializable{

    
    
   
    
    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the Address
     */
    @Override
    public String getAddress() {
       return Address;
    
    }

    /**
     * @param Address the Address to set
     */
    @Override
    public void setAddress(String Address) {
        this.Address = Address;
    }

    private String Email;
    private String Address;
    private String Telephone;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;

    private String acountNumber;
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
     
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
        
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
     return gender;
//  return   gen.get();
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the acountNumber
     */
    public String getAcountNumber() {
        //return acnumber.get();
      return acountNumber;
    }

    /**
     * @param acountNumber the acountNumber to set
     */
    public void setAcountNumber(String acountNumber) {
        this.acountNumber = acountNumber;
    }

    
    @Override
   public  String getEmailAddress(){
    return Email;


   }
   
   
    
    @Override
    public String getTelephone(){
    return Telephone;
  
    }
    
    
    @Override
    public void setEmailAddress(String Email){
         this.Email = Email;
    }
  
  
    @Override
    public void setTelephone(String Telephone){
        this.Telephone = Telephone;
    }
    
 
     
}

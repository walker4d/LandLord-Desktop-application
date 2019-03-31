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
public class LandLord extends Person  implements Serializable{

     
    

//    /**
//     * @return the Firstname
//     */
//    public SimpleStringProperty getFirstname() {
//        return Firstname;
//    }
//
//    /**
//     * @param Firstname the Firstname to set
//     */
//    public void setFirstname(SimpleStringProperty Firstname) {
//        this.Firstname =  Firstname;
//    }
//
//    /**
//     * @return the Lastname
//     */
//    public SimpleStringProperty getLastname() {
//        return Lastname;
//    }
//
//    /**
//     * @param Lastname the Lastname to set
//     */
//    public void setLastname(SimpleStringProperty Lastname) {
//        this.Lastname = Lastname;
//    }
//
//    /**
//     * @return the Telephone
//     */
//    public SimpleStringProperty getTelephone1() {
//        return Telephone;
//    }
//
//    /**
//     * @param Telephone the Telephone to set
//     */
//    public void setTelephone1(SimpleStringProperty Telephone) {
//        this.Telephone = Telephone;
//    }
//
//    /**
//     * @return the Email
//     */
//    public SimpleStringProperty getEmail() {
//        return Email;
//    }
//
//    /**
//     * @param Email the Email to set
//     */
//    public void setEmail(SimpleStringProperty Email) {
//        this.Email = Email;
//    }
//
//    /**
//     * @return the Accountnumber
//     */
//    public SimpleStringProperty getAccountnumber() {
//        return Accountnumber;
//    }
//
//    /**
//     * @param Accountnumber the Accountnumber to set
//     */
//    public void setAccountnumber(SimpleStringProperty Accountnumber) {
//        this.Accountnumber = Accountnumber;
//    }
//
//    /**
//     * @return the gender
//     */
//    public SimpleStringProperty getGender1() {
//        return  this.gender;
//    }
//
//    /**
//     * @param gender the gender to set
//     */
//    public void setGender(SimpleStringProperty gender) {
//        this.gender = gender;
//    }
//public static final String LAND_LORD_FILE = "Save.txt";
    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    
    private  String  Password;
    private  String  ID;
//    
//    
//    private SimpleStringProperty Firstname;
//    private SimpleStringProperty Lastname;
//    private SimpleStringProperty Telephone;
//    private SimpleStringProperty Email;
//    private SimpleStringProperty Accountnumber;
//    private SimpleStringProperty gender;
//    
//    public void SetStrings(){
//    
//        this.setFirstname(new SimpleStringProperty(getFirstName()));
//        this.setLastname(new SimpleStringProperty(getLastName()));
//        this.setTelephone1(new SimpleStringProperty(getTelephone()));
//        this.setEmail(new SimpleStringProperty(getEmailAddress()));
//        this.setAccountnumber(new SimpleStringProperty(getAcountNumber()));
//        this.setGender(new SimpleStringProperty(getGender()));
//        
//        
//        
//    }
    
}

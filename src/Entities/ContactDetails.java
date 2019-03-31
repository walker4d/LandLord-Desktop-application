/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;

/**
 *
 * @author Test
 */
public interface ContactDetails extends Serializable {
    String getEmailAddress();
    String getAddress();
    String getTelephone();
    
    
    void setEmailAddress(String Email);
    void setAddress(String Address);
    void setTelephone(String Telephone);
   
}

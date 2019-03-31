/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Transaction.Transaction;
import Transaction.payments;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Test
 */
public class Tenant extends Person implements Serializable{

 

    /**
     * @return the pay
     */
    public payments getPay() {
        return pay;
    }

    /**
     * @param pay the pay to set
     */
    public void setPay(payments pay) {
        this.pay = pay;
    }
    private String address;
    private payments pay;
       private Transaction transaction;
    
    
     public String getPreAddress(){
        return this.address;
    }
    
    public void setPreAddress(String address){
        this.address = address;
    }

    /**
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
   
 

    
   
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaction;

import java.util.Date;

/**
 *
 * @author Test
 */
public interface CalculateDays {
    static final double PER_WEEK_DAY = 1000;
    static final double PER_WEEKEND_DAY = 1200;
    static final double LATE_FEE_PERCENTAGE = 0.075;
    static final int BILLING_CYCLE = 28;
    static final double FEMALE_DISC = 0.05;
    
    void setPaymentDate(Date day);
    Date getPaymentDate();
    void setTotal(double total);
    double getTotal();
    
    void setLateFee(double lateFee);
    double getLateFee();
    
    void setLastPayment(Date lastPayment);
    Date getLastPayment();
    
    
    //discount amount 
    
    
    
    
}

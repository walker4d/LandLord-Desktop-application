/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaction;

import Entities.Tenant;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Test
 */
public class payments implements CalculateDays ,Serializable {

    /**
     * @return the totalDaysLate
     */
    public int getTotalDaysLate() {
        return totalDaysLate;
    }

    /**
     * @param totalDaysLate the totalDaysLate to set
     */
    public void setTotalDaysLate(int totalDaysLate) {
        this.totalDaysLate = totalDaysLate;
    }

    /**
     * @return the Change
     */
    public double getChange() {
        return Change;
    }

    /**
     * @param Change the Change to set
     */
    public void setChange(double Change) {
        this.Change = Change;
    }

    /**
     * @return the OutStandingAmount
     */
    public double getOutStandingAmount() {
        return OutStandingAmount;
    }

    /**
     * @param OutStandingAmount the OutStandingAmount to set
     */
    public void setOutStandingAmount(double OutStandingAmount) {
        this.OutStandingAmount = OutStandingAmount;
    }

    /**
     * @return the AmountDue
     */
    public double getAmountDue() {
        return AmountDue;
    }

    /**
     * @param AmountDue the AmountDue to set
     */
    public void setAmountDue(double AmountDue) {
        this.AmountDue = AmountDue;
    }

    /**
     * @return the NextPayDate
     */
    public Date getNextPayDate() {
        return NextPayDate;
    }

    /**
     * @param NextPayDate the NextPayDate to set
     */
    public void setNextPayDate(Date NextPayDate) {
        this.NextPayDate = NextPayDate;
    }
    Date DueDate;
    private double Change = 0;
    private Date NextPayDate;
    private double AmountDue = 0;
    private double OutStandingAmount = 0;
   
     private int totalDaysLate = 0;
    private double total;
    private double LateFee;
    private Date lastPayment;
    private Date paymentDate;

    @Override
    public void setPaymentDate(Date day) {
        this.paymentDate = day;
    }

    @Override
    public Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public void setTotal(double total) {
        this.total = total;

    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public void setLateFee(double lateFee) {
        this.LateFee = LateFee;
    }

    @Override
    public double getLateFee() {
        return LateFee;
    }

    @Override
    public void setLastPayment(Date lastPayment) {
        this.lastPayment = lastPayment;
    }

    @Override
    public Date getLastPayment() {
        return lastPayment;
    }

}

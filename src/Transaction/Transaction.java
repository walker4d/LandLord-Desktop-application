/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaction;

import Entities.Tenant;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Test
 */
public class Transaction  implements Serializable{
  private double amountDue;
    private Date nextPayDate;
    private Date lastPayDate;
    private double lateFee;
    private int totalDaysLate;
    private double discAmount;
    private double change;
    private double outsAmt;

    /**
     * @return the amountDue
     */
    
    public double getAmountDue() {
        return amountDue;
    }

    /**
     * @param amountDue the amountDue to set
     */
  
    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    /**
     * @return the nextPayDate
     */
  
    public Date getNextPayDate() {
        return nextPayDate;
    }

    /**
     * @param nextPayDate the nextPayDate to set
     */
    
    public void setNextPayDate(Date nextPayDate) {
        this.nextPayDate = nextPayDate;
    }

    /**
     * @return the lastPayDate
     */
    
    public Date getLastPayDate() {
        return lastPayDate;
    }

    /**
     * @param lastPayDate the lastPayDate to set
     */
  
    public void setLastPayDate(Date lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    /**
     * @return the lateFee
     */
    
    public double getLateFee() {
        return lateFee;
    }

    /**
     * @param lateFee the lateFee to set
     */
  
    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

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
     * @return the discAmount
     */
  
    public double getDiscAmount() {
        return discAmount;
    }

    /**
     * @param discAmount the discAmount to set
     */
    
    public void setDiscAmount(double discAmount) {
        this.discAmount = discAmount;
    }

    /**
     * @return the change
     */

    public double getChange() {
        return change;
    }

    /**
     * @param change the change to set
     */
  
    public void setChange(double change) {
        this.change = change;
    }

    /**
     * @return the out standing Amount
     */
    
    public double getOutsAmt() {
        return outsAmt;
    }

    /**
     * @param outsAmt the out standing Amount to set
     */
   
    public void setOutsAmt(double outsAmt) {
        this.outsAmt = outsAmt;
    }

    public Date nextPayDate(Tenant t) {
        GregorianCalendar calendar = new GregorianCalendar();
        Date newDate;
        calendar.add(Calendar.DAY_OF_WEEK, CalculateDays.BILLING_CYCLE); //28 days
        newDate = calendar.getTime();
        t.getPay().setNextPayDate(newDate);
        
        return newDate;
    }

    public Tenant calAmtDue(Tenant t) {
        calNumDays days = new calNumDays(nextPayDate(t));
        int numWeekDays = days.getCountWkDays();
        int numWeekEnds = days.getCountWKEnds();
        double amtDue = 0;
        double outStandingAmt = t.getPay().getOutStandingAmount();
        double weekDayAmt = numWeekDays * CalculateDays.PER_WEEK_DAY;
        double weekEndAmt = numWeekEnds * CalculateDays.PER_WEEKEND_DAY;
        amtDue = weekDayAmt + weekEndAmt + outStandingAmt;

        amtDue += t.getPay().getLateFee();
        //reset
        t.getPay().setAmountDue(amtDue);
      
        return t;
    }

    //inner class
    private final class calNumDays {

        int countWkDays;
        int countWkEnds;

        //weekdays
        void setCountWkDays(int cwkDays) {
            this.countWkDays = cwkDays;
        }

        //weekEnds
        void setCountWkEnds(int cwkEnds) {
            this.countWkEnds = cwkEnds;
        }

        //get WeekDays
        int getCountWkDays() {
            return this.countWkDays;
        }

        //get weekEndDays
        int getCountWKEnds() {
            return this.countWkEnds;
        }

        public  calNumDays(Date nextD) {
            GregorianCalendar startCal = new GregorianCalendar();
            GregorianCalendar endCal = new GregorianCalendar();
            endCal.setTime(nextD);
            int cWkDays = 0;
            int cWkEnds = 0;
            do {
                if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                        || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    ++cWkEnds;
                } else if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                        && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    ++cWkDays;
                }
                //excluding start date
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());
            //set inner class weekend days and weekdays late
            setCountWkDays(cWkDays);
            setCountWkEnds(cWkEnds);
        }
    }

    public void calLateFee(Tenant t) {
        if (t.getPay().getNextPayDate() != null) {
            GregorianCalendar now = new GregorianCalendar();

            GregorianCalendar nextpayDate = new GregorianCalendar();
            nextpayDate.setTime(t.getPay().getNextPayDate());
            int cWkEnds = 0;
            int cWkDays = 0;
            if (nextpayDate.getTime().after(now.getTime())) {
                //evaulate
                do {
                    if (nextpayDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                            || nextpayDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        ++cWkEnds;
                    } else if (nextpayDate.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                            && nextpayDate.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                        ++cWkDays;
                    }
                    //excluding start date
                    nextpayDate.add(Calendar.DAY_OF_WEEK, 1);
                } while (nextpayDate.getTimeInMillis() > now.getTimeInMillis());

            }
            //add .75% to each late dat
            double LateDaysFee = (cWkDays * CalculateDays.PER_WEEK_DAY)
                    + ((cWkDays * CalculateDays.PER_WEEK_DAY) * CalculateDays.LATE_FEE_PERCENTAGE);

            double LateWKendFee = (cWkEnds * CalculateDays.PER_WEEKEND_DAY)
                    + ((cWkEnds * CalculateDays.PER_WEEKEND_DAY) * CalculateDays.LATE_FEE_PERCENTAGE);
            //total late fee
            double totalLateFee = LateDaysFee + LateWKendFee;
            //total days late
            int daysLate = cWkDays + cWkEnds;
            t.getPay().setLateFee(totalLateFee);
            t.getPay().setTotalDaysLate(daysLate);
        }
    }

    
    
    

}

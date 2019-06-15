/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicefx2;

import Entities.FileInterface;
import Entities.Genertics;
import Entities.LandLord;
import Entities.Tenant;
import Transaction.CalculateDays;
import Transaction.Transaction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Test
 */
public class MakePaymentController implements Initializable {

    @FXML
    private JFXListView<String> tenantInfo;
    @FXML
    private Label name;
    @FXML
    private JFXButton Cancel;
    private ImageView img;
    @FXML
    private Label date;
    private Tenant tenants;
    @FXML
    private TextArea message;
    @FXML
    private JFXTextField amount;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private AnchorPane ppane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // TODO
            date.setText(new Date().toGMTString().toString());
            Tenant tenant;
        try {
            tenant = (Tenant) new Genertics().getAnyObject(FileInterface.TENANT_PAYMENT);
         this.name.setText(tenant.getFirstName() + " " + tenant.getLastName());
    
        
             
         
             setTenants(tenant);
            displayList(tenant);
        } catch (IOException ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
          

     
    }

    

    public void displayList(Tenant tenant) {

        Transaction T = tenant.getTransaction();
       tenant = T.calAmtDue(tenant);
        Double amt = tenant.getPay().getAmountDue();
        tenant.getPay().getOutStandingAmount();
        
        System.out.println("amount :"+amt +"\n"+tenant.getPay().getAmountDue() + " " +         tenant.getPay().getOutStandingAmount());
        String amount = moneyFormat(amt);
        String out = moneyFormat(tenant.getPay().getOutStandingAmount());
        String nextPayDay = tenant.getTransaction().nextPayDate(tenant).toLocaleString();
        String p ="";
       
        ObservableList<String> list = FXCollections.observableArrayList("Amount Due: " + amount, "Next Payment day: " + nextPayDay," OutStanding balance: " +out );

              
        this.tenantInfo.setItems(list);
    }
     @FXML
    private void pay(ActionEvent event) {
     String Message=  this.message.getText();
   String  Amount =   this.amount.getText();
   try{
   if("".equals(Amount)){
         Alert al = new Alert(Alert.AlertType.WARNING, "Payment was not made!!"
                    );
        }else {
            double amt= Double.parseDouble(Amount);
              Alert al = new Alert(Alert.AlertType.INFORMATION, "Payment of $"+Amount+" Was made!!" 
                    );
             makePayments(getTenants(), amt);
             
              if(Message.isEmpty()){
                  
JOptionPane.showMessageDialog(null, "No message was inputed", "Warning", 0);
              }
              
        }
   }catch(NumberFormatException E){

JOptionPane.showMessageDialog(null, "Check your payment Value", "Warning", 0);
    }
       AnchorPane Pane;
        try {
            Pane = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
           ppane.getChildren().setAll(Pane);
        } catch (IOException ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   }
                
    
 public String moneyFormat(double money) {
        String cashAmount;
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        cashAmount = currencyFormatter.format(money);

        return cashAmount;
    }

    public boolean makePayments(Tenant tNew, double pay) {
        //search firts
        Genertics gen = new Genertics();

        GregorianCalendar gc = new GregorianCalendar();

        GregorianCalendar now = new GregorianCalendar();

        if (tNew != null) {

            //get current amount due
            double amtDue = tNew.getPay().getAmountDue();
            //get outstanding amount
            double ouStandAmt = tNew.getPay().getOutStandingAmount();

            double outTemp = 0;

            double changeTemp = 0;

            Tenant nTenant = null;

            Transaction p = new Transaction();

            gc.setTime(tNew.getTransaction().nextPayDate(tNew));

            boolean onTime = false;
            boolean before = false;

            //if the user pays on time
            if (gc.get(GregorianCalendar.DAY_OF_WEEK) == now.get(GregorianCalendar.DAY_OF_WEEK)
                    && gc.get(GregorianCalendar.MONTH) == now.get(GregorianCalendar.MONTH)) {
                if (gc.get(GregorianCalendar.YEAR) == now.get(GregorianCalendar.YEAR)) {
                    onTime = true;
                }
            }

            //pay before due date
            if (now.getTime().before(gc.getTime())) {
                before = true;
            }

            if (onTime) {
                System.out.println("on time");
                if (pay >= amtDue) {
                    changeTemp = pay - amtDue;
                    tNew.getPay().setChange(changeTemp);
                    tNew.getPay().setAmountDue(0);
                
                } else if (pay < amtDue) {
                    outTemp = amtDue - pay;
                    tNew.getPay().setOutStandingAmount(outTemp);
                    tNew.getPay().setChange(0);
                    tNew.getPay().setAmountDue(tNew.getPay().getOutStandingAmount());
                }
                tNew.getPay().setTotalDaysLate(0);
                tNew.getPay().setLateFee(0);
                tNew.getPay().setLastPayment(tNew.getPay().getNextPayDate());
                nTenant = p.calAmtDue(tNew);
                nTenant.getPay().setTotalDaysLate(0);
                nTenant.getPay().setLateFee(0);
                try {
                    update(tNew);
              } catch (IOException ex) {
                    Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (before) {
                try {
                    if (pay >= amtDue) {
                        changeTemp = pay - amtDue;
                        tNew.getPay().setChange(changeTemp);
                        tNew.getPay().setOutStandingAmount(0);
                        tNew.getPay().setAmountDue(0);
                         tNew.getPay().setAmountDue(tNew.getPay().getOutStandingAmount());
      
                        JOptionPane.showMessageDialog(null, "Payment was made sucessfully", "Sucess", 0);
                        
                        JOptionPane.showMessageDialog(null, "new balance is: "+moneyFormat(tNew.getPay().getOutStandingAmount()) , "Information", 0);
                        JOptionPane.showMessageDialog(null, "the change is: "+moneyFormat(tNew.getPay().getChange()) , "Information", 0);
                        
                        
                    } else if (pay < amtDue) {
                        outTemp = amtDue - pay;
                        tNew.getPay().setOutStandingAmount(outTemp);
                        tNew.getPay().setChange(0);
                        tNew.getPay().setAmountDue(ouStandAmt + outTemp);
                         tNew.getPay().setAmountDue(tNew.getPay().getOutStandingAmount());
      
                        JOptionPane.showMessageDialog(null, "Payment was made sucessfully", "Sucess", 0);
                        
                        JOptionPane.showMessageDialog(null, "new balance is: "+moneyFormat(tNew.getPay().getOutStandingAmount()),"Information", 0);
                        JOptionPane.showMessageDialog(null, "the change is: "+moneyFormat(tNew.getPay().getChange()) , "Information", 0);
                        
                    }
                    tNew = p.calAmtDue(tNew);
                    gc.add(Calendar.DAY_OF_WEEK, CalculateDays.BILLING_CYCLE);
                    tNew.getPay().setNextPayDate(gc.getTime());
                    tNew.getPay().setTotalDaysLate(0);
                    tNew.getPay().setLateFee(0);
                    
                    
                    update(tNew);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            } else if (!before && !onTime) {
                System.out.println("Late payment");
                //late payment
                if (pay >= amtDue) {
                    System.out.println("greater than");
                    changeTemp = pay - amtDue;
                    tNew.getPay().setChange(changeTemp);
                    tNew.getPay().setOutStandingAmount(0);
                    tNew.getPay().setAmountDue(0);
                        JOptionPane.showMessageDialog(null, "Payment was made sucessfully", "Sucess", 0);
                        
                        JOptionPane.showMessageDialog(null, "new balance is: "+moneyFormat(tNew.getPay().getOutStandingAmount()),"Information", 0);
                        JOptionPane.showMessageDialog(null, "the change is: "+moneyFormat(tNew.getPay().getChange()) , "Information", 0);
                        
                   
   
                } else if (pay < amtDue) {
                    System.out.println("less than");
                    outTemp = amtDue - pay;
                    tNew.getPay().setOutStandingAmount(outTemp);
                    tNew.getPay().setChange(0);
                    tNew.getPay().setAmountDue(ouStandAmt + outTemp);
                    System.out.println( "new amounut due4: "+tNew.getPay().getOutStandingAmount());
                   JOptionPane.showMessageDialog(null, "Payment was made sucessfully", "Sucess", 0);
                   JOptionPane.showMessageDialog(null, "new balance is: "+moneyFormat(tNew.getPay().getOutStandingAmount()),"Information", 0);
                   JOptionPane.showMessageDialog(null, "the change is: "+moneyFormat(tNew.getPay().getChange()) , "Information", 0);
                        
                }

                tNew.getPay().setLastPayment(now.getTime());
                try {
                    update(tNew);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return false;
    }

    /**
     * @return the tenants
     */
    public Tenant getTenants() {
        return tenants;
    }

    /**
     * @param tenants the tenants to set
     */
    public void setTenants(Tenant tenants) {
        this.tenants = tenants;
    }

   public void update(Tenant t) throws IOException, FileNotFoundException, ClassNotFoundException{
  new Genertics().saveAnyobject(t,FileInterface.TENANT_PAYMENT);
   Map<String,List<Tenant>> updateTenant = null;
 updateTenant= (Map<String, List<Tenant>>) new Genertics().getMapObject(FileInterface.TENANTFILE);
ArrayList<Tenant> list = new ArrayList<Tenant>();
 LandLord landlord = (LandLord) new Genertics().getAnyObject(FileInterface.LOGIN_Temp);
     
       if (updateTenant != null) {
            for (String key : updateTenant.keySet()) {

                if (landlord.getID().equals(key)) {//compair the key with the username 
                    List<Tenant> hashKey = updateTenant.get(key);//gets the key from the file
              
                 ListIterator<Tenant> itr = hashKey.listIterator();
                while(itr.hasNext()){
                    Tenant  l = itr.next();
                           
                        if(t.getEmailAddress().equals(l.getEmailAddress())){
                         
                            list.add(t);
                        }else{
                            list.add(l);
                        }
                        
                    }
                             
                        updateTenant.put(landlord.getID(), list);
                     
                }
   }
}

     
    
 new Genertics().SaveMapObject(updateTenant, FileInterface.TENANTFILE);
  
   }

    @FXML
    private void emailTenant(ActionEvent event) {
        if(!"".equals(this.email.getText())&&!"".equals(this.password.getText())){
        try{
//              LandLord landlord = (LandLord) new Genertics().getAnyObject(FileInterface.LOGIN_Temp);
         
            String host ="smtp.gmail.com" ;
            String user = this.email.getText();//your email;
            String pass = this.password.getText();//password;
            String to = getTenants().getEmailAddress();
            String from = this.email.getText();// email again;
            String subject = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account.";
            String messageText = "" + "Tenant "+ getTenants().getFirstName() + " " + getTenants().getLastName()+"\n" + this.message.getText();
                ;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           JOptionPane.showMessageDialog(null, "message send successfully");;
      
        }catch(Exception ex) {
       
            System.out.println(ex);
        }
        
    }else{
            JOptionPane.showMessageDialog(null, "enter your credentials for your email");
        }
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
     
      AnchorPane Pane = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
       ppane.getChildren().setAll(Pane);


    }
    }
   

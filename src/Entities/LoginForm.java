/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.EOFException;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Test
 */
public class LoginForm implements Serializable {
 public transient Scanner  input = new Scanner(System.in).useDelimiter("\n");  

    /**
     * @return the Land
     */
    public LandLord getLand() {
        return Land;
    }

    /**
     * @param Land the Land to set
     */
    public void setLand(LandLord Land) {
        this.Land = Land;
    }

    private LandLord Land;

    public LandLord confirmLogin(String Id, String password) throws IOException, FileNotFoundException, ClassNotFoundException {

        boolean confirm = true;
        ArrayList<LandLord> ten;//and array reference variable
        Genertics g = new Genertics(); // generic object crated
        LandLord o = new LandLord();//land lord object to be returned
        ten = (ArrayList<LandLord>) g.getArrayObject(FileInterface.LAND_LORDFILE);//reading the landlords data from a file
        ListIterator<LandLord> l = ten.listIterator();//listiterator to be loop through the data in the list


        while (l.hasNext()) {
            LandLord land;

            land = l.next();

            if ((Id.equals(land.getID())) && (password.equals(land.getPassword()))) {//checking if the password and ID matched with a date in the list
                if ("Male".equals(land.getGender())) {
                    System.out.println("Login sucessfully\n Welcome MR. " + land.getLastName()+ " success");

                } else if ("female".equals(land.getGender())) {
                    System.out.println("Login sucessfully\n Welcome Miss. " + land.getLastName() + " Success");

                } else {
                    System.out.println( "Login sucessfully\n Welcome  " + land.getLastName() + " Sucess");

                }

                o = land;
                confirm = true;
                
                return o;
                

            } else {
                System.out.println("password or user ID did not matched " + " Failed");
                //return if the pass word and id did not matched 

                confirm = false;
            }

        }

        return null;

    }
    //function of that log in the lnadlords
      public void login() throws IOException, FileNotFoundException, ClassNotFoundException {

        String ID;
        String password;

        LandLord landlord = new LandLord();

        
        System.out.println("enter your login information\n Enter your ID");
        ID = input.nextLine();
        System.out.println("Enter your passWord");
        password = input.nextLine();

        //returning the confirmation  data  to the landlord object
        landlord = confirmLogin(ID, password);
        if (landlord == null) {//checking if the object is null and if it is returned a message
            System.out.println("Fail to login ");
        } else {//else set the land lord data and call a submenu
            setLand(landlord);
            submenu();

        }
    }
//submenu 
    public void submenu() throws IOException, FileNotFoundException, EOFException, ClassNotFoundException {

        int decision = 0;
       
        do {
            System.out.println("Enter Option");
            System.out.println("[1]Display Tenant information");
            System.out.println("[2]Add a tenant");
            System.out.println("[5] Exits");

            decision = input.nextInt();
            switch (decision) {
                case 1:
                    try {
                        display();//calling the display method
                    } catch (NullPointerException l) {
                        System.out.println("Null exception");
                    }

                    break;

                case 2:
//calling the registration method
                    TenantRegistration();

                    break;

                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    return;

            }

        } while (decision > 0);
    }

    //registering/ adding tenant information to a file
    public void TenantRegistration() throws IOException, FileNotFoundException, EOFException, ClassNotFoundException {

     Tenant T = new Tenant();
String fn,ln,G,Ac,prA,Em,num="";
        System.out.println("fill out the current information");

        System.out.println("Enter Tenant's First Name ");
      fn = input.next();
        T.setFirstName(fn);

        System.out.println("Enter Tenant's Last Name ");
        ln = input.next();
        T.setLastName(ln);
        System.out.println("Enter your Gender");
        G = input.next().toUpperCase();
        T.setGender(G);

        System.out.println("Enter Tenant's Account Number ");
        Ac = input.next();
        T.setAcountNumber(Ac);

        System.out.println("Enter Tenant's Previous Address ");
           prA = input.next();
        T.setPreAddress(prA);

        boolean k = true;
        while (k == true) {
            System.out.println("Enter Tenant's Email Address Eg:'annettewalker328@gmail.com' ");
          Em = input.next();
            T.setEmailAddress(Em);
//email validation
            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(T.getEmailAddress());
            if (!matcher.matches()) {
                System.out.println("Email not valid ");

            } else {
                k = false;
            }
        }

        System.out.println("Enter Tenant's Telephone number ");

        T.setTelephone(input.next());

        System.out.println("Tele: "+T.getTelephone());
        save(T);

    }
//a method that save the tenant and update inforamtion from a file
    public void save(Tenant t)
            throws FileNotFoundException, IOException, EOFException, ClassNotFoundException {
        try {

           //getting the landlord's id
            String ID = getLand().getID();
          
            Genertics G = new Genertics();
            List<Tenant> l = new ArrayList<>();
           
            
            
            Map<String,List<Tenant>> Write = new HashMap<String,List<Tenant>>();
            //readinf from a file using Genertics object
            Write = (Map<String, List<Tenant>>) G.getMapObject("");
        

           
            if (Write == null) {//checking if file  or list is empty when read
                System.out.println("file is empty");
                Write = new HashMap<String,List<Tenant>>();

            
                Write.put(ID, l);
            } else { 
                //searching for a specific key in the hash mapp and adding to that specific key
                for(String key: Write.keySet()){
            if(key.equals(ID)){
            List<Tenant> ten = Write.get(key);
             ten.add(t);
             l = ten;
            
        }
                
                }

                 //adding information to the map
                Write.put(ID, l);
            }
//saving the map information 
           G.SaveMapObject(Write,"");
           

System.out.println("Information saved sucessfully...");

        } catch (IndexOutOfBoundsException x) {
            System.out.println(x.fillInStackTrace() + " fail");
        } catch (NullPointerException x) {
            System.out.println("null pointer Exception " + x.fillInStackTrace());
        }

    }
//display method
    public void display() throws IOException, FileNotFoundException, ClassNotFoundException {
    
        LandLord land = getLand();
   
        
        Genertics G = new Genertics();
        Map<String, List<Tenant>> Write;
        Write = (Map<String, List<Tenant>>) G.getMapObject("");
        Write.keySet().stream().filter((key) -> (key.equals(land.getID()))).forEachOrdered((key) -> {
            List<Tenant> ten = Write.get(key);
            //Iterator in list
            Iterator<Tenant> pl = ten.iterator();
            while (pl.hasNext()) {
                
                Tenant t = pl.next();
                System.out.println("Name: " +t.getFirstName() + " " + t.getLastName() + "\n" + "Account Number: " + t.getAcountNumber() + "\n" +
                 "Ammount due: " + "$0.0" + "\n" + "Gender: " + t.getGender() + "\n" + "telephone: " + t.getTelephone() + "\n" 
                + "Previous address: " + t.getPreAddress()+ "\nEmail: " + t.getEmailAddress() + "\n\n");
                
            }
            

     }); 
  

      }

       }
    
    
    


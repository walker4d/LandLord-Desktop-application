package practicefx2;

import Entities.FileInterface;
import Entities.Genertics;
import Entities.LandLord;
import Entities.Tenant;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

public class FXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane RootPane;

    @FXML
    private Label label;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private Button Login;

    @FXML
    private JFXTextField user;

    @FXML
    private Button Register;

    @FXML
    void LoginAction(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {

        LandLord landlord = new LandLord();
        String password = this.pass.getText();

        String username = this.user.getText();

        //returning the confirmation  data  to the landlord object
        landlord = confirmLogin(username, password);
        if (landlord == null) {//checking if the object is null and if it is returned a message
            JOptionPane.showMessageDialog(null, "Username and Password did not Matched", "Information", 0);

        } else {//else set the land lord data and call a submenu
//            setLand(landlord);
            if ("Male".equals(landlord.getGender())) {
                System.out.println();

                JOptionPane.showMessageDialog(null, "Login sucessfully\n Welcome MR. " + landlord.getLastName(), " success", 0);

            } else if ("female".equals(landlord.getGender())) {
                System.out.println("Login sucessfully\n Welcome Miss. " + landlord.getLastName() + " Success");
                //               JOptionPane.showMessageDialog(null, "Username and Password did not Matched", "Information", 0);

            } else {

                JOptionPane.showMessageDialog(null, "Login sucessfully\n Welcome MR. " + landlord.getLastName(), " success", 0);

            }
            Genertics g = new Genertics();
            g.saveAnyobject(landlord, FileInterface.LOGIN_Temp);
            AnchorPane Pane = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
            RootPane.getChildren().setAll(Pane);

        }

    }

    @FXML
    void RegisterEvent(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        RootPane.getChildren().setAll(Pane);

    }

    private LandLord Land;

    public LandLord confirmLogin(String Id, String password) throws IOException, FileNotFoundException, ClassNotFoundException {
       
            System.out.println("Confirming...");
            boolean confirm = true;
            ArrayList<LandLord> ten = null;//and array reference variable
            Genertics g = new Genertics(); // generic object crated
            LandLord o = new LandLord();//land lord object to be returned
            System.out.println("about to read file");
            ten = (ArrayList<LandLord>) g.getArrayObject(FileInterface.LAND_LORDFILE);
                  if(ten != null){                                                  //reading the landlords data from a file
            ListIterator<LandLord> l = ten.listIterator();//listiterator to be loop through the data in the list

            System.out.println("iterating...");
            
            
            while (l.hasNext()) {
          
                LandLord land;

                land = l.next();
                System.out.println(land.getID() + "ID :" + Id);
                if ((Id.equals(land.getID())) && (password.equals(land.getPassword()))) {
                
                    //checking if the password and ID matched with a date in the list

                    System.out.println("Returing...");
                    
                   
                   
                    return land;

                } else {
                    System.out.println("not found ");
                    //System.out.println("not found :" + Id +" " +Land.getID()+" " +password + " " + land.getPassword());
                       confirm = true;
                }

            }
                  }else{
                      JOptionPane.showMessageDialog(null, "please sign up first", "information", 0);
                  }
      
        return null;

    }

    @FXML
    void initialize() {
        assert RootPane != null : "fx:id=\"RootPane\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert Login != null : "fx:id=\"Login\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert Register != null : "fx:id=\"Register\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

    }
}

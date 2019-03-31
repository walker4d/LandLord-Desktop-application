/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicefx2;

import Entities.FileInterface;
import Entities.Genertics;
import Entities.LandLord;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RegistrationController {

    @FXML
    private ToggleGroup Group;
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
   
    
    
    public void save(LandLord landLordList) throws FileNotFoundException, IOException,EOFException, ClassNotFoundException{
 try{
  


Genertics G  = new Genertics(); 

ArrayList<LandLord> Write = new ArrayList<>();
Write = (ArrayList<LandLord>)G.getArrayObject(FileInterface.LAND_LORDFILE);

if(Write== null){
Write = new ArrayList<>();
Write.add(landLordList);
}else{
   LandLord c = Write.get(0);
     System.out.println(c.getFirstName() + c.getLastName());
    Write.add(landLordList);
}

G.SaveArrayObject(Write, FileInterface.LAND_LORDFILE);


   JOptionPane.showMessageDialog(null,"Information Saved Succesfuly....", "Save",0 );
	  Iterator<LandLord> pl = Write.iterator();
        while(pl.hasNext()){
            
            LandLord m = pl.next();
           System.out.println("From file: " + m.getFirstName()+" " + m.getID()+ ", " + m.getPassword()+ m.getGender());

           
            
        }


 }catch(IndexOutOfBoundsException x ){
     System.out.println(x.fillInStackTrace() + " fail");
 }

    }

  public boolean regexTeleValidator(String telephoneNo) {

        String telNoRegex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";//pattern
        Pattern telPattern = Pattern.compile(telNoRegex, Pattern.CASE_INSENSITIVE);//compile telRegex into pattern
        Matcher matcher = telPattern.matcher(telephoneNo);//match user input
        if (matcher.find()) { //find the next subsequence of the input sequence that matches the pattern.
            System.out.println("");
            return true;
        } else {
            return false;
        }
    }
  
    
        @FXML
    private AnchorPane RegistrationLPane;
    


    @FXML
    private TextField ID;

    @FXML
    private TextField LN;

    @FXML
    private TextField password;

    @FXML
    private TextField Cpassword;

    @FXML
    private TextField Email;

    @FXML
    private TextField FN;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXButton Cancel;

    @FXML
    private JFXButton uploadButton;

    @FXML
    private TextField Tele;

    @FXML
    private TextField Address;
    
    @FXML
    private TextField Accountnumber;
   
    @FXML
    private ImageView imageView;


    @FXML
    private JFXRadioButton femaleOption;

    @FXML
    private JFXRadioButton MaleOPtion;

        @FXML
    void cancelAction(ActionEvent event) throws IOException {
AnchorPane Pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
RegistrationLPane.getChildren().setAll(Pane);
    }
    public String option() {
        Group = new ToggleGroup();

        this.MaleOPtion.setToggleGroup(Group);

        this.femaleOption.setToggleGroup(Group);

        String gender = "";
        if (MaleOPtion.isSelected()) {
            gender = MaleOPtion.getText();
        } else if (femaleOption.isSelected()) {
            gender = femaleOption.getText();
        }

        return gender;

    }
    @FXML
    void submitAction(ActionEvent event) throws IOException, FileNotFoundException, EOFException, ClassNotFoundException {
       if(!"".equals(this.Email.getText())&& !"".equals(this.FN.getText())&&!"".equals(LN.getText())&&!"".equals(this.Address.getText())&&!"".equals(this.Tele.getText())&&!"".equals(this.Accountnumber.getText())){
       String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(this.Email.getText());
            if (matcher.matches()) {
                 
            if(regexTeleValidator(this.Tele.getText())==true){
        LandLord landLord = new LandLord();
        String pass;
        String confirmPass;
        
       
         landLord.setFirstName(this.FN.getText());
         landLord.setEmailAddress(this.Email.getText()); 
     
        landLord.setLastName(this.LN.getText());
        
        
              landLord.setGender(option());
        
           landLord.setImage(getFpath());
       landLord.setAddress(this.Address.getText());



        landLord.setTelephone(this.Tele.getText());

    
        
    
        landLord.setAcountNumber(this.Accountnumber.getText());
        
               

        landLord.setID(this.ID.getText());
       pass =this.password.getText();
        
    
        confirmPass = this.Cpassword.getText();
        boolean b = true;

       
        
        
        if(pass.equals(confirmPass)){
              
        landLord.setPassword(confirmPass);
        save(landLord);
        
        b = false;
AnchorPane Pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
RegistrationLPane.getChildren().setAll(Pane);
        }else {
            JOptionPane.showMessageDialog(null, "Password enterd did not Match", "Error", 0);
     
        
      
        }
            }else{
                             JOptionPane.showMessageDialog(null, "Telephone number is not valid", "Error", 0);

            }
            } else {
        
             JOptionPane.showMessageDialog(null, "Email is not valid", "Error", 0);
      
            }
            }else{
           JOptionPane.showMessageDialog(null, "Please Make sure all feilds have been inputed", "Error", 0);
      
       }
           
        
    }
    
public String f = "";
public void setFpath(String f){
    this.f = f;
}
public String getFpath(){
    return this.f;
}
    @FXML
    void uploadAction(ActionEvent event) {
 if (!"".equals(this.Tele.getText())) {

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(null);

            //get or the select file image
            BufferedImage image = null;
            try {

                image = ImageIO.read(file.getAbsoluteFile());
                WritableImage i = SwingFXUtils.toFXImage(image, null);
                imageView.setImage(i);
            } catch (IOException ex) {
                System.out.println("Fail to Read");
            }

            String imagePath = FilePath(file.getAbsolutePath());
//          );
            setFpath(imagePath);
            File toWrite = new File(imagePath);
            String extension = "";
            File f = new File("images//");

            int i = imagePath.lastIndexOf('.');
            if (i >= 0) {
                extension = imagePath.substring(i + 1);
            }
            if (!toWrite.exists()) {
                try {
                     f.mkdir();
              
                    toWrite.getAbsoluteFile().getParentFile().mkdir();
                    toWrite.createNewFile();

                    ImageIO.write(image, extension, toWrite);
                } catch (IOException ex) {

                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please make sure all feilds have been entered", "Warning!", 0);
        }

    }

    public String FilePath(String path) {
        File f = new File("MainForm.fxml");
        String filep = f.getAbsolutePath();
        String strNew = filep.replace("MainForm.fxml", "\\");
        String extension = "";
        int i = path.lastIndexOf('.');
        if (i >= 0) {
            extension = path.substring(i + 1);
        }
        System.out.println(strNew + "images\\LandLord\\" + this.Tele.getText() + "." + extension);
        return strNew + "images\\LandLord\\" + this.Tele.getText() + "." + extension;
    }

    
    
    void initialize() {
        assert ID != null : "fx:id=\"ID\" was not injected: check your FXML file 'Registration.fxml'.";
        assert LN != null : "fx:id=\"LN\" was not injected: check your FXML file 'Registration.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'Registration.fxml'.";
        assert Cpassword != null : "fx:id=\"Cpassword\" was not injected: check your FXML file 'Registration.fxml'.";
        assert Email != null : "fx:id=\"Email\" was not injected: check your FXML file 'Registration.fxml'.";
        assert FN != null : "fx:id=\"FN\" was not injected: check your FXML file 'Registration.fxml'.";
        assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'Registration.fxml'.";
        assert Cancel != null : "fx:id=\"Cancel\" was not injected: check your FXML file 'Registration.fxml'.";
        assert uploadButton != null : "fx:id=\"uploadButton\" was not injected: check your FXML file 'Registration.fxml'.";
        assert Tele != null : "fx:id=\"Tele\" was not injected: check your FXML file 'Registration.fxml'.";
        assert Address != null : "fx:id=\"Address\" was not injected: check your FXML file 'Registration.fxml'.";
        assert femaleOption != null : "fx:id=\"femaleOption\" was not injected: check your FXML file 'Registration.fxml'.";
        assert MaleOPtion != null : "fx:id=\"MaleOPtion\" was not injected: check your FXML file 'Registration.fxml'.";

    }
}

    


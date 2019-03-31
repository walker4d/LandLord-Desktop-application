package practicefx2;

import Entities.FileInterface;
import Entities.Genertics;
import Entities.LandLord;
import Entities.Tenant;
import Transaction.CalculateDays;
import Transaction.Transaction;
import Transaction.payments;
import com.jfoenix.controls.JFXRadioButton;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddTenantController {

    @FXML
    private AnchorPane tenantAnchor;

    @FXML
    private ImageView img;

    @FXML
    private TextField fn;

    @FXML
    private TextField ln;

    @FXML
    private TextField email;

    @FXML
    private TextField tele;

    @FXML
    private TextField account;

    public String fpath = "";
    @FXML
    private JFXRadioButton MaleOPtion;
    @FXML
    private ToggleGroup Group;
    @FXML
    private JFXRadioButton femaleOption;

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }

    public String getFpath() {
        return this.fpath;
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        tenantAnchor.getChildren().setAll(Pane);
    }
public String email(String email){
    String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(this.email.getText());
            if (!matcher.matches()) {
              JOptionPane.showMessageDialog(null, "Email is not valid", "Error", 0);
      
            } else {
    return email;
            }
    return "";
}
    @FXML
    void confirm(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {
       
        if(!"".equals(this.email.getText())&& !"".equals(this.fn.getText())&&!"".equals(ln.getText())&&!"".equals(this.tele.getText())&&!"".equals(this.account.getText())){
        
            LandLord landlord;
        String image = getFpath();
        Genertics g = new Genertics();
        landlord = (LandLord) g.getAnyObject(FileInterface.LOGIN_Temp);
        Tenant tenant = new Tenant();
        payments pay = new payments();
        Transaction T = new Transaction();
       String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(this.email.getText());
            if (matcher.matches()) {
              if(regexTeleValidator(this.tele.getText())==true){   
        tenant.setEmailAddress(this.email.getText());
        tenant.setFirstName(this.fn.getText());
        tenant.setLastName(this.ln.getText());
        
         tenant.setTelephone(this.tele.getText());
            
      
        tenant.setGender(option());
        tenant.setPay(pay);
        tenant.setAcountNumber(this.account.getText());
        tenant.setImage(image);
        tenant.setTransaction(T);
        tenant = T.calAmtDue(tenant);
        
        

        save(tenant, landlord);
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        tenantAnchor.getChildren().setAll(Pane);
              }else{
                  JOptionPane.showMessageDialog(null, "Telephone number does not make any sense");
              }
              }else{
JOptionPane.showMessageDialog(null, "Email not valid");

            }
    }else{
            JOptionPane.showMessageDialog(null, "Please make sure all feilds have been selected");
            }
            

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

    public void save(Tenant t, LandLord land)
            throws FileNotFoundException, IOException, EOFException, ClassNotFoundException {

        //getting the landlord's id
        String ID = land.getID();
 List<Tenant> ten = null;
        Genertics G = new Genertics();

        Map<String, List<Tenant>> Write = new HashMap<String, List<Tenant>>();
        //reading from a file using Genertics object
        Write = (Map<String, List<Tenant>>) G.getAnyObject(FileInterface.TENANTFILE);
        File toWrite = new File(FileInterface.TENANTFILE);
        if (Write == null) {
            
            
            List<Tenant> l = new ArrayList<>();
           
            System.out.println("file is empty");
            Write = new HashMap<String, List<Tenant>>();
            l.add(t);
            
            Write.put(ID, l);
            System.out.println(t.getFirstName());

        }else {
            //searching for a specific key in the hash mapp and adding to that specific key
            
                
            for(String key : Write.keySet()) {
                if (key.equals(ID)) {
                   ten = Write.get(key);
                    ten.add(t);
                Write.put(ID, ten);
                 

                }else{
                    ten = new ArrayList<Tenant>();
                    ten.add(t);
                     Write.put(ID, ten);
                }
                  

            }

            //adding information to the map
        }

        //saving the map information 
        G.saveAnyobject(Write, FileInterface.TENANTFILE);

        System.out.println("Information saved sucessfully...");

    }

    @FXML
    void uploadaction(ActionEvent event) {
        if (!"".equals(this.tele.getText())) {

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(null);

            //get or the select file image
            BufferedImage image = null;
            try {

                image = ImageIO.read(file.getAbsoluteFile());
                WritableImage i = SwingFXUtils.toFXImage(image, null);
                img.setImage(i);
            } catch (IOException ex) {
                System.out.println("Fail to Read");
            }

            String imagePath = FilePath(file.getAbsolutePath());
//          );
            setFpath(imagePath);
            File toWrite = new File(imagePath);
            String extension = "";

//                   setFileLocation(toWrite.toString());
            int i = imagePath.lastIndexOf('.');
            if (i >= 0) {
                extension = imagePath.substring(i + 1);
            }
            if (!toWrite.exists()) {
                try {
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
        System.out.println(strNew + "images\\Tenant\\" + this.tele.getText() + "." + extension);
        return strNew + "images\\Tenant\\" + this.tele.getText() + "." + extension;
    }

    void initialize() {
        assert tenantAnchor != null : "fx:id=\"tenantAnchor\" was not injected: check your FXML file 'AddTenant.fxml'.";
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'AddTenant.fxml'.";
        assert fn != null : "fx:id=\"fn\" was not injected: check your FXML file 'AddTenant.fxml'.";
        assert ln != null : "fx:id=\"ln\" was not injected: check your FXML file 'AddTenant.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'AddTenant.fxml'.";
        assert tele != null : "fx:id=\"tele\" was not injected: check your FXML file 'AddTenant.fxml'.";
        assert account != null : "fx:id=\"account\" was not injected: check your FXML file 'AddTenant.fxml'.";

    }
}

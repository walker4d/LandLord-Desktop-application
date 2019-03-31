package practicefx2;

import Entities.FileInterface;
import Entities.Genertics;
import Entities.LandLord;
import Entities.Tenant;
import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class MaineFormController implements Initializable {

    @FXML
    private TableView<Tenant> TenantTable;

    @FXML
    private JFXButton Load_data;

    @FXML
    private Label fn;

    @FXML
    private Label ln;
    @FXML
    private AnchorPane MainPane;

    @FXML
    private JFXButton addTenant;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton ExitButton;
    public LandLord landlord;
    @FXML
    private ImageView ImageDisplay;
    @FXML
    private Label fname;
    @FXML
    private Label lname;
    @FXML
    private Label email;
    @FXML
    private ImageView landImg;
public void imgDisplay(LandLord l){
     File file = new File(l.getImage());
            BufferedImage image = null;
            try {

                image = ImageIO.read(file.getAbsoluteFile());
                WritableImage i = SwingFXUtils.toFXImage(image, null);

                landImg.imageProperty().set(i);

            } catch (IOException ex) {
                System.out.println("Fail to Read");
            }
}
    public void setlandlord(LandLord landlord) {
        this.landlord = landlord;
    }

    public LandLord getLandLord() {
        return this.landlord;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // TODO
            loadon();
        } catch (IOException ex) {
            Logger.getLogger(MaineFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaineFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loadon() throws IOException, FileNotFoundException, ClassNotFoundException {
        LandLord landlord;
        Genertics g = new Genertics();
        landlord = (LandLord) g.getAnyObject(FileInterface.LOGIN_Temp);
        imgDisplay(landlord);
        Date d = new Date();
        setlandlord(landlord);
        this.fn.setText("Name: " + landlord.getFirstName() + " " + landlord.getLastName());
        this.ln.setText("Date: " + d.toLocaleString());
        TablePopulate();

    }

    @FXML
    void ExitAction(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void loadAction(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {

        loadon();

    }

    @FXML
    void SignupTenant(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("AddTenant.fxml"));
        MainPane.getChildren().setAll(Pane);

    }

    @FXML
    void logoutAction(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        MainPane.getChildren().setAll(Pane);

    }

    public void TablePopulate() throws IOException, FileNotFoundException, ClassNotFoundException {
        TableColumn<Tenant, String> Firstname = new TableColumn("Firstname");
        TableColumn<Tenant, String> LastName = new TableColumn("Lastname");
        TableColumn<Tenant, String> Telephone = new TableColumn("Telephone#");
        TableColumn<Tenant, String> Email = new TableColumn("Email");
        TableColumn<Tenant, String> AccountNumber = new TableColumn("Acount#");
        TableColumn<Tenant, String> Gender = new TableColumn("Gender");

        TenantTable.getColumns().addAll(Firstname, LastName, Telephone, Email, AccountNumber, Gender);

        Firstname.setCellValueFactory(new PropertyValueFactory<Tenant, String>("FirstName"));

        LastName.setCellValueFactory(new PropertyValueFactory<Tenant, String>("LastName"));

        Gender.setCellValueFactory(new PropertyValueFactory<Tenant, String>("Gender"));

        Email.setCellValueFactory(new PropertyValueFactory<Tenant, String>("EmailAddress"));

        Telephone.setCellValueFactory(new PropertyValueFactory<Tenant, String>("Telephone"));

        AccountNumber.setCellValueFactory(new PropertyValueFactory<Tenant, String>("AcountNumber"));
        TenantTable.setItems(getUserList());

    }

    public ObservableList<Tenant> getUserList() throws IOException, FileNotFoundException, ClassNotFoundException {
        ObservableList<Tenant> list = FXCollections.observableArrayList();
        Genertics G = new Genertics();
        Map<String, List<Tenant>> tenantList = (Map<String, List<Tenant>>) G.getMapObject(FileInterface.TENANTFILE);
        if (tenantList != null) {
            for (String key : tenantList.keySet()) {

                if (getLandLord().getID().equals(key)) {//compair the key with the username 
                    List<Tenant> hashKey = tenantList.get(key);//gets the key from the file
                   if(hashKey !=null)
                    for (Tenant t : hashKey) {
                        list.add(t);

                    }
                }
             //return list;
            }
        } else {
           list =  null;
        }

        return list;
    }
    public Tenant t;

    public void setTenant(Tenant t) {
        this.t = t;
    }

    public Tenant getTenant() {

        return t;
    }

    @FXML
    private void display(MouseEvent event) {
        ImageDisplay.imageProperty().set(null);
        Tenant t = this.TenantTable.getSelectionModel().getSelectedItem();

        //          System.out.println(tenant.getPay().getAmountDue());
//            Double amt = tenant.getPay().getAmountDue();
//            String amount = amt.toString();
            System.out.println( "Out standing balance " +t.getPay().getOutStandingAmount());
           System.out.println("Next pay day: \n" + t.getTransaction().nextPayDate(t).toString());
          System.out.println("amount??"+t.getTransaction().getOutsAmt()+"\namount: "+t.getPay().getAmountDue());
          
//          t.getPay().setChange(00.00);
       //  double d = tenant.getPay().getChange();
         
      
                
       
       // T.calAmtDue(tenant);

        if (t != null) {
        
            this.fname.setText(t.getFirstName());
            this.lname.setText(t.getLastName());
            this.email.setText(t.getEmailAddress());
            setTenant(t);
            File file = new File(t.getImage());
            BufferedImage image = null;
            try {

                image = ImageIO.read(file.getAbsoluteFile());
                WritableImage i = SwingFXUtils.toFXImage(image, null);

                ImageDisplay.imageProperty().set(i);

            } catch (IOException ex) {
                System.out.println("Fail to Read");
            }
        } else {
            Alert al = new Alert(Alert.AlertType.WARNING, "make sure you click a field and then display button"
                    + "\n to dispplay tenant information");
        }
    }

    @FXML
    private void makePayment(ActionEvent event) throws IOException {
       if(getTenant() != null){
        Genertics G = new Genertics();
        G.saveAnyobject(getTenant(), FileInterface.TENANT_PAYMENT);
       AnchorPane Pane = FXMLLoader.load(getClass().getResource("Makepayment.fxml"));
        MainPane.getChildren().setAll(Pane);
       }else {
           JOptionPane.showMessageDialog(null, "please selsect a tenant");
       }

    }
}

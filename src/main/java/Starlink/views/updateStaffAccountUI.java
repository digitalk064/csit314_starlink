

 package Starlink.views;

 import com.jfoenix.controls.JFXButton;
 import com.jfoenix.controls.JFXPasswordField;
 import com.jfoenix.controls.JFXTextField;
 
 import Starlink.Starlink;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.scene.control.Label;
 import javafx.scene.layout.AnchorPane;
 import javafx.stage.Stage;
 import javafx.scene.Node;

public class updateStaffAccountUI {

    
    String id, staffname,staffusername,staffemail,staffpassword;
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXPasswordField passowordField;

    @FXML
    private JFXTextField staffnamefield;

    @FXML
    private JFXTextField staffidField;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private Label staffid;

    @FXML
    private Label username;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label password;

    @FXML
    void onBackClicked(ActionEvent event) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("SearchHealthStaffAccUI.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void onHomeClicked(ActionEvent event) throws Exception{

    
        Parent root = FXMLLoader.load(getClass().getResource("homepage_admin.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();

    

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception
    {
        Logout(event);
    }

    void Logout(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onUpdateBtnClicked(ActionEvent event) {

        Stage stage = Starlink.getStage();
        System.out.println("Update button pressed");
        //Get the user's text input from the fields
        id = staffidField.getText();
        staffname = staffnamefield.getText();
        staffusername = usernameField.getText();
        staffemail = emailField.getText();
        staffpassword= passowordField.getText();

        //validate
        //dialog box

    }

}

    


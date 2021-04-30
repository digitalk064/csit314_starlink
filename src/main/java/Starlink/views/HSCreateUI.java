package Starlink.views;
//package Starlink.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Starlink.Starlink;
import Starlink.controllers.HSCreateController;
import Starlink.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class HSCreateUI {

    String staffid, staffname, staffpassword, staffemail, staffusername ;
    HSCreateController control;
    Stage stage;


    //fxml elements 

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton CreateButton;

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
    void onCreateBtnClicked(ActionEvent event) {

        stage = Starlink.getStage();
        System.out.println("Create button pressed");
        //Get the user's text input from the fields
        staffid = staffidField.getText();
        staffname = staffnamefield.getText();
        staffusername = usernameField.getText();
        staffemail = emailField.getText();
        staffpassword= passowordField.getText();

        //validate
        //todo
        //need to create the confirmation prompt 
        //todo

    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("HSManagingPage.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();

    }

 
    @FXML
    void onHomeClicked(ActionEvent event) throws Exception {
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

}

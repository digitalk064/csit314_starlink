package Starlink.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Starlink.Starlink;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;


public class HSSuspendEditUI {

    String staffid ;
    //HSCreateController control;
    Stage stage;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXTextField inputSearchText;

    @FXML
    private ComboBox<?> dropMenu;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton SearchButton;

    
    @FXML
    void onBackClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("homepage_admin.fxml"));
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


    @FXML
    void onSearchClicked(ActionEvent event) throws Exception{

        stage = Starlink.getStage();
        System.out.println("Create button pressed");
        //Get the user's text input from the fields
        staffid = inputSearchText.getText();

        //validate
        //todo

        //switch scene after creating the search button
        // to get the list of the staff id to suspend or edit 
        Parent root = FXMLLoader.load(getClass().getResource("HSSuspendPage.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
        

    }

    @FXML
    void selectEmailName(ActionEvent event) {
    //TO DO
    //selecting from the drop down list


    }

   


}
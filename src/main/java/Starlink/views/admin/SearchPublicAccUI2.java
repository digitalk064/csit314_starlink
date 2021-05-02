package Starlink.views.admin;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
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


public class SearchPublicAccUI2 {

    String userid, username;
    
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton suspendButton;

    @FXML
    private Label usernamelabel;

    @FXML
    private Label useridlabel;

    @FXML
    private JFXTextField idfield;

    @FXML
    private JFXTextField namefield;

    @FXML
    void onHomeClicked(ActionEvent event) throws Exception{

    
        Parent root = FXMLLoader.load(getClass().getResource("homepage_admin.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();

    

    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("SearchPublicAccUI.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/Starlink/views/login.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void onSuspendClicked(ActionEvent event) {

        Stage stage = Starlink.getStage();
        System.out.println("Create button pressed");
        //Get the user's text input from the fields
        userid = idfield.getText();
        username = namefield.getText();
        //dialog box

    }

    @FXML
    void onUpdateBtnClicked(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("updatePublicAccountUI.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();

    }
    
}

    


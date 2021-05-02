package Starlink.views.admin;

import Starlink.Starlink;
import Starlink.controllers.general.userLoginController;
import Starlink.entities.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSpinner;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AdminHomepageUI {
    //Logged in user
    User user;

    Stage stage;

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXButton manageHealthButton;

    @FXML
    private JFXButton managePublicButton;

    @FXML
    private JFXButton manageBusinessButton;


    @FXML
    private Text header;

    //FXML callbacks
    @FXML
    protected void initialize() //Called when the view is loaded
    {
        stage = Starlink.getStage();
        //Get the logged in user
        user = (User)stage.getUserData();
        header.setText(String.format("Hello, %s. You are a %s", user.getID(), user.getUserType()));
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

//Switching scene template
    @FXML
void onManageBusinessAccountClicked(ActionEvent event) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("SearchBusinessAccUI.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();

}

@FXML
void onManageHealthStaffAccountClicked(ActionEvent event) throws Exception
{
    Parent root = FXMLLoader.load(getClass().getResource("SearchHealthStaffAccUI.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
}

@FXML
void onManagePublicUserAccountClicked(ActionEvent event) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("SearchPublicAccUI.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();

}
}






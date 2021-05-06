package Starlink.views.publicUser;

import Starlink.Starlink;
import Starlink.controllers.general.userLoginController;
import Starlink.entities.User;
import Starlink.views.CommonUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

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

public class PublicUserHomepageUI extends CommonUI {

    User user;

    @FXML
    private Text header;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private Label vaccineStatus;


    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }
    
    @FXML
    protected void initialize() // Called when the view is loaded
    {
        super.initialize();
        // Get the logged in user
        user = (User) stage.getUserData();
        header.setText(String.format("Hello, %s. You are a %s", user.getID(), user.getUserType()));
    }


    

    


    
}



   


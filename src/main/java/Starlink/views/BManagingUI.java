package Starlink.views;

import Starlink.Starlink;
import Starlink.controllers.LoginController;
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
public class BManagingUI {

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton SuspendEditBusinessButton;

    @FXML
    private JFXButton CreateBusinessAccountButton;

    @FXML
    private JFXButton BacktoHomepageButton;

    @FXML
    void SuspendEditBusinessAccountClicked(ActionEvent event) {

    }


    @FXML
    void onCreateBusinessAccountClicked(ActionEvent event) {

    }

    @FXML
    void onBacktoHomepageClicked(ActionEvent event) throws Exception
    {
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


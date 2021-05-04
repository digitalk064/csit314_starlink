package Starlink.views.admin;

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
import com.jfoenix.controls.JFXTextField;

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

public class viewCertPublicUserUI extends CommonUI {

    @FXML
    private Label nameField;

    @FXML
    private Label ICField;

    @FXML
    private Label vaccNameField;

    @FXML
    private Label batchnoField;

    @FXML
    private Label dateField;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXButton logoutButton;
   
    @FXML
    void onBackClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("homepage_publicUser.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void onHomeClicked(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("homepage_publicUser.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {

        Logout();

    }

}



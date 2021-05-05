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


public class generateCertHealthStaffUI extends CommonUI  {

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField ICField;

    @FXML
    private JFXTextField vaccNameField;

    @FXML
    private JFXTextField batchnoField;

    @FXML
    private JFXTextField dateField;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton generateButton;


    @FXML
    void onGenerateClicked(ActionEvent event) {


        //get the health staff input
        String name = nameField.getText(); 
        String identificationNo = ICField.getText(); 
        String batchNo = batchnoField.getText(); 
        String vaccineName = vaccNameField.getText(); 
        String date = dateField.getText(); 


    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("homepage_HealthStaff.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void onHomeClicked(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("homepage_HealthStaff.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {

        Logout();

    }

}

package Starlink.views.business;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class BusinessMessageUI extends CommonUI {

    @FXML
    private JFXButton acknowledgeButton;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private Label businessNameLabel;

    @FXML
    private Label BusinessLocationLabel;

    @FXML
    private Label BusinessDateLabel;

    @FXML
    void onAcknowledgeClicked(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("NotificationBusinessUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NotificationBusinessUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void onHomeClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("homepage_Business.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }


}

package Starlink.views.publicUser;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class NotificationPublicUserUI extends CommonUI{

    @FXML
    private JFXButton message1;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXButton Message2;

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homepage_publicUser.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void onHomeClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("homepage_publicUser.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }

    @FXML
    void onMessage1Clicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("PublicUserMessageUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onMessage2Clicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("PublicUserMessageUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

}

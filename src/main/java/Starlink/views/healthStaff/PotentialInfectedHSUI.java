package Starlink.views.healthStaff;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class PotentialInfectedHSUI extends CommonUI {

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXListView<?> resultDisplayList;

    @FXML
    private JFXButton alertUser;

    @FXML
    private JFXButton AlertBusiness;

    @FXML
    void onAlertBusinessClicked(ActionEvent event) {

    }

    @FXML
    void onAlertUserClicked(ActionEvent event) {

    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("homepage_HealthStaff.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();

    }

    @FXML
    void onHomeClicked(ActionEvent event) throws Exception {

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

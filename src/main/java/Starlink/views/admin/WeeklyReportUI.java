package Starlink.views.admin;

import com.jfoenix.controls.JFXButton;

import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class WeeklyReportUI extends CommonUI {

    @FXML
    private JFXButton BacktoHomepageButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private Label fromDateWeekRepLabel;

    @FXML
    private Label toDateWeekRepLabel;

    @FXML
    private Label casesWeeklyRepLabel;

    @FXML
    private Label vaxWeeklyRepLabel;

    @FXML
    private Label avgCaseLabel;

    @FXML
    private Label avgVaxLabel;


    @FXML
    void onBacktoHomepageClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homepage_admin.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }

}

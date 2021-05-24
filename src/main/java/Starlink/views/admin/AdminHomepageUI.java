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

public class AdminHomepageUI extends CommonUI {
    // Logged in user
    User user;

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXButton manageHealthButton;

    @FXML
    private JFXButton managePublicButton;

    @FXML
    private JFXButton manageBusinessButton;

    @FXML
    private JFXButton dailyreportBtn;

    @FXML
    private JFXButton weeklyreportBtn;

    @FXML
    private Text header;

    // FXML callbacks
    @FXML
    protected void initialize() // Called when the view is loaded
    {
        super.initialize();
        // Get the logged in user
        user = (User) stage.getUserData();
        header.setText(String.format("Hello, %s.", user.getID()));
    }

    @FXML
    void onWeeklyRepClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("WeeklyReportUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void onDailyRepClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("DailyReportUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }

    // Switching scene template
    @FXML
    void onManageBusinessAccountClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SearchBusinessAccUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onManageHealthStaffAccountClicked(ActionEvent event) throws Exception {
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

package Starlink.views.admin;

import com.jfoenix.controls.JFXButton;

import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class DailyReportUI extends CommonUI {

    @FXML
    private JFXButton BacktoHomepageButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private Label dateDailyRepLabel;

    @FXML
    private Label casesDailyRepLabel;

    @FXML
    private Label vaxDailyRepLabel;

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

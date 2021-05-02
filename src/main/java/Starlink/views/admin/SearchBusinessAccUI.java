package Starlink.views.admin;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Starlink.Starlink;
import Starlink.views.CommonUI;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SearchBusinessAccUI extends CommonUI {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton SearchButton;

    @FXML
    private JFXButton CreateBusinessAccountButton;

    @FXML
    private JFXButton BacktoHomepageButton;

    @FXML
    private JFXComboBox<?> businessid;

    @FXML
    private JFXTextField inputtextfield;

    @FXML
    void onCreateBusinessAccountClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("createBusinessAccountUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onSearchClicked(ActionEvent event) throws IOException {

    }

    @FXML
    void onBacktoHomepageClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homepage_admin.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onSuspendClicked(ActionEvent event) {

        // dialog box
    }

    @FXML
    void onUpdateBtnClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("updateBusinessAccountUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }

}

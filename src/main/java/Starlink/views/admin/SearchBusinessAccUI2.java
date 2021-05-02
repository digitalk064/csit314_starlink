package Starlink.views.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Starlink.Starlink;
import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SearchBusinessAccUI2 extends CommonUI {

    String businessid, businessname;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton suspendButton;

    @FXML
    private Label businessnamelabel;

    @FXML
    private Label businessidlabel;

    @FXML
    private JFXTextField idfield;

    @FXML
    private JFXTextField namefield;

    @FXML
    void onHomeClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("homepage_admin.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("SearchBusinessAccUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }

    @FXML
    void onSuspendClicked(ActionEvent event) {

        System.out.println("Create button pressed");
        // Get the user's text input from the fields
        businessid = idfield.getText();
        businessname = namefield.getText();
        // dialog box
    }

    @FXML
    void onUpdateBtnClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("updateBusinessAccountUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

}

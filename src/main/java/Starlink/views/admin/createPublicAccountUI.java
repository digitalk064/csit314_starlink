package Starlink.views.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import Starlink.Starlink;
import Starlink.controllers.admin.createPublicAccountController;
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

public class createPublicAccountUI extends CommonUI {

    String id, name, publicusername, useremail, userpassword;
    createPublicAccountController controller;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton CreateButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField userfullnamefield;

    @FXML
    private JFXTextField IDNumField;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXTextField emailField;

    @FXML
    protected void initialize() // Called when the view is loaded
    {
        super.initialize();
        controller = new createPublicAccountController();
        for (Node node : anchorpane.getChildren()) {
            if (node instanceof JFXTextField)
                ((JFXTextField) node).getValidators().add(new RequiredFieldValidator("This field is required"));
        }
        passwordField.getValidators().add(new RequiredFieldValidator("This field is required"));
    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("SearchPublicAccUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onCreateBtnClicked(ActionEvent event) throws Exception {
        try {
            for (Node node : anchorpane.getChildren()) {
                if (node instanceof JFXTextField)
                    ((JFXTextField) node).validate();
            }
            passwordField.validate();

            System.out.println("Create button pressed");
            // Get the user's text input from the fields
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            String IDNum = IDNumField.getText();
            String name = userfullnamefield.getText();
            if (controller.verifyInput(username, password, email, IDNum, name))
                showSuccess();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    void showSuccess() {
        CreateDialog(anchorpane, "Success", "Public User account successfully created.");
    }

    void showError(String errorMsg) {
        CreateDialog(anchorpane, "Error", "Failed to create account. Error message: \n" + errorMsg);
    }

    @FXML
    void onHomeClicked(ActionEvent event) throws Exception {

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

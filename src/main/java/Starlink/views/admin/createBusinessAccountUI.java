package Starlink.views.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import Starlink.Starlink;
import Starlink.controllers.admin.createBusinessController;
import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class createBusinessAccountUI extends CommonUI {
    String id, businessname, businessusername, businessemail, businesspassword;
    createBusinessController controller;
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
    private JFXTextField idField;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField addressField;

    @FXML
    protected void initialize() // Called when the view is loaded
    {
        super.initialize();
        controller = new createBusinessController();
        for (Node node : anchorpane.getChildren()) {
            if (node instanceof JFXTextField)
                ((JFXTextField) node).getValidators().add(new RequiredFieldValidator("This field is required"));
        }
        passwordField.getValidators().add(new RequiredFieldValidator("This field is required"));
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
            String businessID = idField.getText();
            String name = userfullnamefield.getText();
            String address = addressField.getText();
            if (controller.verifyInput(username, password, email, businessID, name, address))
                showSuccess();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    void showSuccess() {
        CreateDialog(anchorpane, "Success", "Business account successfully created.");
    }

    void showError(String errorMsg) {
        CreateDialog(anchorpane, "Error", "Failed to create account. Error message: \n" + errorMsg);
    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SearchBusinessAccUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

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

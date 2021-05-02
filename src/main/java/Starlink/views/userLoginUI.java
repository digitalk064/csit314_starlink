package Starlink.views;

import Starlink.controllers.general.userLoginController;
import Starlink.entities.HealthOrganization;
import Starlink.entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

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

public class userLoginUI extends CommonUI {

    String username, password;
    userLoginController control;

    // FXML elements
    @FXML
    private Button loginButton;

    @FXML
    private StackPane rootPane;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    // FXML callbacks
    @FXML
    protected void initialize() // Called when the view is loaded
    {
        super.initialize();
        System.out.println("Login FXML loaded");
        // JFXSpinner spin = new JFXSpinner();
        // rootPane.getChildren().add(spin);
        // Create the controller
        control = new userLoginController();
    }

    @FXML
    void onSubmit(ActionEvent event) throws Exception {
        stage = (Stage) rootPane.getScene().getWindow();
        System.out.println("Login button pressed");
        // Get the user's text input from the fields
        username = usernameField.getText();
        password = passwordField.getText();

        // Check with controller here
        try {
            User user = control.validate(username, password);
            // If we reach this code, login successful
            // Save the logged in User into the stage
            stage.setUserData(user);
            goToHomePage(user);
        } catch (Exception e) {
            // Show error dialog
            e.printStackTrace();
            showInvalid(e.getMessage());
        }
    }

    void showInvalid(String errorMsg) {
        CreateDialog(rootPane, "Error", "Failed to login. Error message: \n" + errorMsg);
    }

    // Misc functions
    boolean validateFields() {
        return true;
    }

    // Switching scene template
    void goToHomePage(User user) throws Exception {
        String url = "";
        if (user instanceof HealthOrganization)
            url = "admin/homepage_admin.fxml";
        else // Other homepages later
            url = "homepage_test.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}

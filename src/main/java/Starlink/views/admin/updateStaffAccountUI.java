package Starlink.views.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Starlink.Starlink;
import Starlink.controllers.admin.updateHealthStaffController;
import Starlink.entities.HealthStaff;
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

public class updateStaffAccountUI extends CommonUI {

    String id, staffname, staffusername, staffemail, staffpassword;
    updateHealthStaffController controller;
    int targetUserID;
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField staffnamefield;

    @FXML
    private JFXTextField staffidField;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXTextField emailField;

    @FXML
    protected void initialize() // Called when the view is loaded
    {
        super.initialize();
        controller = new updateHealthStaffController();
    }

    public void initFields(HealthStaff healthStaff)
    {
        staffidField.setText(healthStaff.getStaffID());
        staffnamefield.setText(healthStaff.getName());
        usernameField.setText(healthStaff.getUsername());
        emailField.setText(healthStaff.getEmail());
        passwordField.setText(healthStaff.getPassword());
        targetUserID = healthStaff.getID();
    }


    @FXML
    void onBackClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("SearchHealthStaffAccUI.fxml"));
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

    @FXML
    void onSubmit(ActionEvent event) {

        System.out.println("Update button pressed");
        // Get the user's text input from the fields
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String staffID = staffidField.getText();
        String name = staffnamefield.getText();
        try{
            if(controller.verifyInput(targetUserID, username, password, email, staffID, name))
                showSuccess();
        }
        catch(Exception e)
        {
            showError(e.getMessage());
        }
    }

    void showSuccess()
    {
        CreateDialog(anchorpane, "Success", "The account information has been updated");
    }

    void showError(String errorMsg)
    {
        CreateDialog(anchorpane, "Error", "Could not update the account information. Error message:\n" + errorMsg);
    }

}

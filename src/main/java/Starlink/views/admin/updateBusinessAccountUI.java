package Starlink.views.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Starlink.Starlink;
import Starlink.controllers.admin.updateBusinessAccountController;
import Starlink.entities.Business;
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

public class updateBusinessAccountUI extends CommonUI {

    int targetUserID; //User ID of the account being updated

    updateBusinessAccountController controller;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton UpdateButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField userfullnamefield;

    @FXML
    private JFXTextField businessIDField;

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
        controller = new updateBusinessAccountController();
    }

    public void initFields(Business business)
    {
        addressField.setText(business.getAddress());
        businessIDField.setText(business.getBusinessID());
        userfullnamefield.setText(business.getName());
        usernameField.setText(business.getUsername());
        emailField.setText(business.getEmail());
        passwordField.setText(business.getPassword());
        targetUserID = business.getID();
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

    @FXML
    void onSubmit(ActionEvent event) {
        // Get the user's text input from the fields
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String businessID = businessIDField.getText();
        String name = userfullnamefield.getText();
        String address = addressField.getText();
        try{
            if(controller.verifyInput(targetUserID, username, password, email, businessID, name, address))
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

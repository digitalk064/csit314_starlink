package Starlink.views.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Starlink.Starlink;
import Starlink.controllers.admin.updatePublicAccountController;
import Starlink.entities.PublicUser;
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

public class updatePublicAccountUI extends CommonUI {
    String id, name, publicusername, useremail, userpassword;
    int targetUserID; //User ID of the account being updated

    updatePublicAccountController controller;
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
        controller = new updatePublicAccountController();
    }

    public void initFields(PublicUser publicUser)
    {
        IDNumField.setText(publicUser.getIDNum());
        userfullnamefield.setText(publicUser.getName());
        usernameField.setText(publicUser.getUsername());
        emailField.setText(publicUser.getEmail());
        passwordField.setText(publicUser.getPassword());
        targetUserID = publicUser.getID();
    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("SearchPublicAccUI.fxml"));
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
    void onSubmit(ActionEvent event) throws Exception{

        System.out.println("Update button pressed");
        // Get the user's text input from the fields
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String IDNum = IDNumField.getText();
        String name = userfullnamefield.getText();
        try{
            if(controller.verifyInput(targetUserID, username, password, email, IDNum, name))
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

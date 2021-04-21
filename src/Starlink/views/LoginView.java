package Starlink.views;

import Starlink.controllers.LoginController;

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

public class LoginView {

    String username, password;
    LoginController control;

    //FXML elements
    @FXML
    private Button loginButton;

    @FXML
    private StackPane rootPane;
    
    @FXML
    private JFXTextField usernameField;
    
    @FXML
    private JFXPasswordField passwordField;

    //FXML callbacks
    @FXML
    protected void initialize() //Called when the view is loaded
    {
        System.out.println("Login FXML loaded");
        //JFXSpinner spin = new JFXSpinner();
        //rootPane.getChildren().add(spin);
        //Create the controller
        control = new LoginController();
    }    

    @FXML
    void onLoginClicked(ActionEvent event) throws Exception
    {
        System.out.println("Login button pressed");
        //Get the user's text input from the fields
        username = usernameField.getText();
        password = passwordField.getText();

        //Validate fields at view level
        if(!validateFields()){
            
            return;
        }
        //Check with controller here
        if(control.validateLogin(username, password)){
            //Login successful
            goToHomePage(event);
        }
        else
        {
            //Show error dialog (just testing, will only show text later)
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error"));
            content.setBody(new Text("You have entered something invalid."));
            JFXDialog dialog = new JFXDialog(rootPane, content, JFXDialog.DialogTransition.CENTER);
            dialog.show(rootPane);
        }
    }

    //Misc functions
    boolean validateFields()
    {
        return true;
    }

    //Switching scene template
    void goToHomePage(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("homepage_test.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

}
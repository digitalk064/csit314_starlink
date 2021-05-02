

package Starlink.views.admin;

import java.net.PasswordAuthentication;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import Starlink.Starlink;
import Starlink.controllers.admin.createHealthStaffController;
import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class createHealthStaffUI {

    //Controllers
    createHealthStaffController controller;

    Stage stage ;
    //String id, staffname,staffusername,staffemail,staffpassword;
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
    private JFXPasswordField passowordField;

    @FXML
    private JFXTextField staffnamefield;

    @FXML
    private JFXTextField staffidField;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXTextField emailField;

    //FXML callbacks
    @FXML
    protected void initialize() //Called when the view is loaded
    {
        controller = new createHealthStaffController();
        for (Node node : anchorpane.getChildren()) {
            if(node instanceof JFXTextField)
                ((JFXTextField)node).getValidators().add(new RequiredFieldValidator("This field is required"));
        }
        passowordField.getValidators().add(new RequiredFieldValidator("This field is required"));
    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("SearchHealthStaffAccUI.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onCreateBtnClicked(ActionEvent event) throws Exception {
        try{
            for (Node node : anchorpane.getChildren()) {
                if(node instanceof JFXTextField)
                    ((JFXTextField)node).validate();
            }
            passowordField.validate();
            stage = Starlink.getStage();
            System.out.println("Create button pressed");
            //Get the user's text input from the fields
            String username = usernameField.getText();
            String password= passowordField.getText();
            String email = emailField.getText();
            String staffID = staffidField.getText();
            String name = staffnamefield.getText();
            if(controller.verifyInput(username, password, email, staffID, name))
                showSuccess();
        }
        catch(Exception e)
        {
            showError(e.getMessage());
        }
    }
    
    void showSuccess()
    {
        CommonUI.CreateDialog(anchorpane, "Success", "Health Staff account successfully created.");
    }

    void showError(String errorMsg)
    {
        CommonUI.CreateDialog(anchorpane, "Error", "Failed to create account. Error message: \n" + errorMsg);
    }

    @FXML
    void onHomeClicked(ActionEvent event) throws Exception{

    
        Parent root = FXMLLoader.load(getClass().getResource("homepage_admin.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();

    

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception
    {
        Logout(event);
    }

    void Logout(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Starlink/views/login.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

}

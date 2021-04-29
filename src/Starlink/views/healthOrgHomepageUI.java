package Starlink.views;

import com.jfoenix.controls.JFXButton;

import Starlink.Starlink;
import Starlink.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;


public class healthOrgHomepageUI {

    @FXML
    private AnchorPane pane;

    @FXML
    private Text header;

     //FXML callbacks
     @FXML
     protected void initialize() //Called when the view is loaded
     {
         Stage stage = Starlink.getStage();
         //Get the logged in user
         User user = (User)stage.getUserData();
         header.setText(String.format("Hello, %s. You are a %s", user.getID(), user.getUserType()));
     }

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton manageHealthButton;

    @FXML
    private JFXButton manageBusinessButton;

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout(event);

    }

    void Logout(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onManageBusinessAccountClicked(ActionEvent event) {

    }

    @FXML
    void onManageHealthStaffAccountClicked(ActionEvent event) {

    }

}

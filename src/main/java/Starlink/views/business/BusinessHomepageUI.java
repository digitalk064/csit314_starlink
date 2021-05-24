package Starlink.views.business;

import com.jfoenix.controls.JFXButton;

import Starlink.entities.User;
import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class BusinessHomepageUI extends CommonUI {

    // Logged in user
    User user;

    @FXML
    private JFXButton notificationbutton;

    @FXML
    private Text header;

    @FXML
    private JFXButton logoutButton;

    // FXML callbacks
    @FXML
    protected void initialize() // Called when the view is loaded
    {
        super.initialize();
        // Get the logged in user
        user = (User) stage.getUserData();
        header.setText(String.format("Hello, %s.", user.getID()));
    }


    @FXML
    void onLogoutClicked(ActionEvent event)throws Exception {
        Logout();
    }

    @FXML
    void onNotificationClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("NotificationBusinessUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();


    }

}

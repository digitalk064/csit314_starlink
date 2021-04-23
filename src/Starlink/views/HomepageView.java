package Starlink.views;

import Starlink.controllers.LoginController;
import Starlink.entities.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSpinner;

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

public class HomepageView {
    @FXML
    private Text header;

    //FXML callbacks
    @FXML
    protected void initialize() //Called when the view is loaded
    {
        header.setText(String.format("Hello, %s. You are a %s", User.session.getID(), User.session.getUserType()));
    }
}

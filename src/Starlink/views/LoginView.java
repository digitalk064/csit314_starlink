package Starlink.views;

import javafx.fxml.FXML;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


public class LoginView {
    @FXML
    private Button loginButton;

    @FXML
    private StackPane rootPane;

    @FXML
    void onLoginClicked(ActionEvent event)
    {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Dialog"));
        content.setBody(new Text("This is how u show a dialog I guess"));
        JFXDialog dialog = new JFXDialog(rootPane, content, JFXDialog.DialogTransition.CENTER);
        dialog.show(rootPane);
        System.out.println("Login");
    }
}

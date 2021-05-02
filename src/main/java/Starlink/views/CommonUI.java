package Starlink.views;

import Starlink.Starlink;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class CommonUI {
    Stage stage;
    
    public static void CreateDialog(Pane pane, String header, String body){
        StackPane stack = new StackPane();
            stack.setPrefSize(1280, 800);
            pane.getChildren().add(stack);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text(header));
            content.setBody(new Text(body));
            JFXDialog dialog = new JFXDialog(stack, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button=new JFXButton("OKAY");
            button.setOnAction(ev -> {
                dialog.close();
            });
            content.setActions(button);
            dialog.show(stack);
            dialog.setOnDialogClosed(handler -> pane.getChildren().remove(stack));
    }

    public void Logout() throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Starlink/views/login.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}

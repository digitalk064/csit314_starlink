package Starlink.views.business;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;

import Starlink.controllers.publicUser.BusinessNotificationController;

import Starlink.entities.Business;
import Starlink.entities.Alert;

import Starlink.views.CommonUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class NotificationBusinessUI extends CommonUI{
    Business user;

    BusinessNotificationController controller;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXListView<Label> resultDisplayList;

    //Because each result row is a FXML file with the controller being set to this
    //When we load the results they will call the initialize method again which is wrong
    //So we set a flag to prevent them from calling the initialize method
    boolean isRowItem = false;
    public void setIsRowItem()
    {
        isRowItem = true;
    }

    @FXML
    protected void initialize()// Called when the view is loaded
    {
        if(isRowItem)
            return;
        super.initialize();
        // Get the logged in user
        user = (Business) stage.getUserData();
        controller = new BusinessNotificationController();
        try{
            GetAllAlerts();
        }catch(Exception e)
        {
            System.out.println("Error while getting alerts:");
            e.printStackTrace();
        }
    }

    List<Alert> alerts;
    
    void GetAllAlerts() throws Exception
    {
        alerts = controller.getAlerts(user);
        System.out.println("Got " + alerts.size() + " alerts.");
        DisplayAlerts();
    }

    void DisplayAlerts() throws Exception
    {
        resultDisplayList.getItems().clear(); 
        
        for(int i = 0; i < alerts.size(); i++){
            Label btn = new Label(alerts.get(i).getMessage());
            btn.setId(String.valueOf(i));
            resultDisplayList.getItems().add(btn);
        }
    }

    @FXML
    void onAlertClicked(MouseEvent event) throws Exception {
        if(resultDisplayList.getSelectionModel().getSelectedIndex() == -1)
            return;
        int alert = Integer.parseInt(resultDisplayList.getSelectionModel().getSelectedItem().getId());
        System.out.println("Clicked on " + alert);
        showAlert(alert);
    }

    @FXML
    void showAlert(int alert) throws Exception
    {
        StackPane stack = new StackPane();
        stack.setPrefSize(1280, 800);
        rootPane.getChildren().add(stack);
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Alert"));
        content.setBody(new Label(alerts.get(alert).getMessage()));
        JFXDialog dialog = new JFXDialog(stack, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("ACKNOWLEDGE");
        button.setOnAction(ev -> {
            dialog.close();
        });
        content.setActions(button);
        dialog.show(stack);
        dialog.setOnDialogClosed(handler -> 
            {
                try{
                    rootPane.getChildren().remove(stack);
                    controller.deleteAlert(alerts.get(alert));
                    //Refresh the alerts list
                    resultDisplayList.getItems().remove(resultDisplayList.getSelectionModel().getSelectedItem());
                    resultDisplayList.getSelectionModel().clearSelection();
                }catch(Exception e)
                {
                    System.out.println("Error while trying to delete alert:");
                    e.printStackTrace();
                }
                
            }
        );
    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homepage_Business.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void onHomeClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("homepage_Business.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }
}
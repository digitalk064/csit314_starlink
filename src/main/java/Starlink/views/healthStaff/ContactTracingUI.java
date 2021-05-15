package Starlink.views.healthStaff;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import Starlink.controllers.healthStaff.AlertBusinessController;
import Starlink.controllers.healthStaff.AlertPublicUserController;
import Starlink.controllers.healthStaff.ContactTracingController;
import Starlink.entities.LocHistory;
import Starlink.entities.PublicUser;
import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ContactTracingUI extends CommonUI {

    String targetIDNum; //User identification number being traced
    ContactTracingController controller;
    AlertPublicUserController alertPublicController;
    AlertBusinessController alertBusinessController;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text header;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXListView<Pane> resultDisplayList;

    @FXML
    private JFXButton alertUser;

    @FXML
    private JFXButton AlertBusiness;

    List<LocHistory> results_locations; // The location history of other users who were present
    List<Integer> users = new ArrayList<>(); // The list of unique public users' userID who were in contact
    List<Integer> businesses = new ArrayList<>(); // The list of unique businesses' userID who the person went to

    //Because each result row is a FXML file with the controller being set to this
    //When we load the results they will call the initialize method again which is wrong
    //So we set a flag to prevent them from calling the initialize method
    boolean isRowItem = false;
    public void setIsRowItem()
    {
        isRowItem = true;
    }

    @FXML
    protected void initialize() // Called when the view is loaded
    {
        if(isRowItem)
            return;
        super.initialize();
        controller = new ContactTracingController();
        alertPublicController = new AlertPublicUserController();
        alertBusinessController = new AlertBusinessController();
    }

    public void initFields(PublicUser publicuser) throws Exception
    {
        targetIDNum = publicuser.getIDNum();
        header.setText("Tracing contacts for " + publicuser.getName() + ".");
        try{
            results_locations = controller.getContacts(targetIDNum);
            DisplayAllContacts();
        }catch(Exception e)
        {
            System.out.println("Error while getting contacts list:");
            e.printStackTrace();
        }
    }

    void DisplayAllContacts() throws Exception
    {
        System.out.println("Found " + results_locations.size() + " contacts.");
        resultDisplayList.getItems().clear(); 

        for(int i = 0; i < results_locations.size(); i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Starlink/views/healthStaff/misc/contactRowTemplateHS.fxml"));
            loader.setController(ContactTracingUI.this);
            ((ContactTracingUI)loader.getController()).setIsRowItem();
            Pane resultRow =  loader.load();
            //Get the public user's IDNum and add it to the list if it's not already in
            int publicUserID = results_locations.get(i).getPublicUserID();
            if(!users.contains(publicUserID))
                users.add(publicUserID);
            //Get the business's ID and add it to the list if it's not already in
            int businessUserID = results_locations.get(i).getBusinessUserID();
            if(!businesses.contains(businessUserID))
                businesses.add(businessUserID);
            ((Label)resultRow.lookup("#rowIDNum")).setText(results_locations.get(i).getIDNum());
            ((Label)resultRow.lookup("#rowName")).setText(results_locations.get(i).getUserName());
            ((Label)resultRow.lookup("#rowAddress")).setText(results_locations.get(i).getAddress());
            ((Label)resultRow.lookup("#rowCheckIn")).setText(results_locations.get(i).getCheckIn());
            resultDisplayList.getItems().add(resultRow);
        }
    }

    @FXML
    void onAlertBusinessesClicked(ActionEvent event) {
        try{
            if(alertBusinessController.generateAlert(businesses))
                showSuccess();
        }catch(Exception e)
        {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    @FXML
    void onAlertUsersClicked(ActionEvent event) {
        try{
            if(alertPublicController.generateAlerts(users))
                showSuccess();
        }catch(Exception e)
        {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    void showSuccess()
    {
        CreateDialog(rootPane, "Success", "Successfully sent alerts to users.");
    }

    void showError(String errorMsg)
    {
        CreateDialog(rootPane, "Error", "Failed to send alerts to users. Error message:\n" + errorMsg);
    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("homepage_HealthStaff.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();

    }

    @FXML
    void onHomeClicked(ActionEvent event) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("homepage_HealthStaff.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {

        Logout();
    }



}

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

    PublicUser target; //User being traced
    ContactTracingController controller;
    AlertPublicUserController alertPublicController;
    AlertBusinessController alertBusinessController;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text header;

    @FXML
    private Label legendLabel;

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

    List<LocHistory> contactRecords; // The location history of other users who were present
    List<Integer> businesses = new ArrayList<>(); // The list of unique businesses' userID who the person went to
    List<String> times = new ArrayList<>(); // The list of times the user last visited the businesses (mapped 1:1 to businesses variable)
    //List<Pair<String, String>> businessTimes;

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
        target = publicuser;
        header.setText("Tracing contacts for " + publicuser.getName() + ".");
        try{
            contactRecords = controller.getContacts(publicuser.getIDNum());
            DisplayAllContacts();
        }catch(Exception e)
        {
            System.out.println("Error while getting contacts list:");
            e.printStackTrace();
        }
        legendLabel.setText("Black outline = location visited by " + target.getName() + ". No outline = location visited by other users who came into contact with " + target.getName() + ".");
    }

    void DisplayAllContacts() throws Exception
    {
        System.out.println("Found " + contactRecords.size() + " contacts.");
        resultDisplayList.getItems().clear(); 

        for(int i = 0; i < contactRecords.size(); i++){
            //Get the business's ID and add it to the list if it's not already in
            int businessUserID = contactRecords.get(i).getBusinessUserID();
            if(!businesses.contains(businessUserID)){
                businesses.add(businessUserID);
                times.add(contactRecords.get(i).getCheckIn());
            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Starlink/views/healthStaff/misc/contactRowTemplateHS.fxml"));
            loader.setController(ContactTracingUI.this);
            ((ContactTracingUI)loader.getController()).setIsRowItem();
            Pane resultRow =  loader.load();

            ((Label)resultRow.lookup("#rowIDNum")).setText(contactRecords.get(i).getIDNum());
            ((Label)resultRow.lookup("#rowName")).setText(contactRecords.get(i).getUserName());
            ((Label)resultRow.lookup("#rowAddress")).setText(contactRecords.get(i).getAddress());
            ((Label)resultRow.lookup("#rowCheckIn")).setText(contactRecords.get(i).getCheckIn());
            
            //If this row contains the record for the target public user, display the row as outlined
            if(contactRecords.get(i).getIDNum() == target.getIDNum()){
                resultRow.setStyle("-fx-border-color: #000000");
                ((Label)resultRow.lookup("#rowName")).setText(target.getName());
            }

            resultDisplayList.getItems().add(resultRow);
        }
    }

    @FXML
    void onAlertBusinessesClicked(ActionEvent event) {
        try{
            if(alertBusinessController.generateAlert(businesses, times))
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
            if(alertPublicController.generateContactAlerts(contactRecords))
                showSuccess();
        }catch(Exception e)
        {
            e.printStackTrace();
            showError(e.getMessage());
        }
    }

    void showSuccess()
    {
        CreateDialog(rootPane, "Success", "Successfully sent alerts.");
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

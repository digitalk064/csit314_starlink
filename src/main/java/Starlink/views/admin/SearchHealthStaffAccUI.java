package Starlink.views.admin;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import Starlink.Starlink;
import Starlink.controllers.admin.HealthStaffAccSuspendController;
import Starlink.controllers.admin.SearchHealthStaffAccController;
import Starlink.entities.HealthStaff;
import Starlink.views.CommonUI;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SearchHealthStaffAccUI extends CommonUI {
    //Controllers
    SearchHealthStaffAccController searchController;
    HealthStaffAccSuspendController suspendController;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton SearchButton;

    @FXML
    private JFXButton CreateHealthStaffAccountButton;

    @FXML
    private JFXButton BacktoHomepageButton;

    @FXML
    private JFXTextField inputtextfield;

    @FXML
    private JFXListView<Pane> resultDisplayList;

    //Because each result row is a FXML file with the controller being set to this
    //When we load the results they will call the initialize method again which is wrong
    //So we set a flag to prevent them from calling the initialize method
    boolean isRowItem = false;
    public void setIsRowItem()
    {
        isRowItem = true;
    }

    @FXML 
    private JFXComboBox<String> searchByDropdown;

    @FXML
    protected void initialize() // Called when the view is loaded
    {
        if(isRowItem)
            return;
        super.initialize();
        searchController = new SearchHealthStaffAccController();
        suspendController = new HealthStaffAccSuspendController();
        searchByDropdown.getItems().add("Staff ID");
        searchByDropdown.getItems().add("Staff name");
        searchByDropdown.setValue("Staff ID");
        try{
            onSearchClicked(null);
        }catch(Exception e)
        {
            System.out.println("Error in onSearchClicked:");
            e.printStackTrace();
        }
    }

    @FXML
    void onCreateHealthStaffAccountClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("createHealthStaffUI.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onBacktoHomepageClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homepage_admin.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }

    List<HealthStaff> results;

    @FXML
    void onSearchClicked(ActionEvent event) throws Exception {
        if(searchByDropdown.getValue().equals("Staff ID"))
            results = searchController.validateByID(inputtextfield.getText());
        else
            results = searchController.validateByName(inputtextfield.getText());
        System.out.println("Results count: " + results.size());
        DisplayResult();
    }
    
    void DisplayResult() throws Exception
    {
        resultDisplayList.getItems().clear(); 
        for(int i = 0; i < results.size(); i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Starlink/views/admin/misc/searchResultRowTemplate.fxml"));
            loader.setController(this);
            ((SearchHealthStaffAccUI)loader.getController()).setIsRowItem();
            Pane resultRow =  loader.load();
            JFXButton suspendBtn = (JFXButton)resultRow.lookup("#suspendBtn");
            if(results.get(i).getSuspended().equals("yes"))
            {
                suspendBtn.setDisable(true);
                suspendBtn.setText("Suspended");
            }
            else{
                suspendBtn.setId(String.valueOf(i));
            }
            ((JFXButton)resultRow.lookup("#editBtn")).setId(String.valueOf(i));
            Label label = (Label)resultRow.lookup("#resultRowLabel");
            label.setText(String.format("%-10s%-30s", results.get(i).getStaffID(), results.get(i).getName()));
            resultDisplayList.getItems().add(resultRow);
        }
    }

    @FXML
    void onSuspendClicked(ActionEvent event) throws Exception{
        int index = Integer.parseInt(((Node) event.getSource()).getId());
        System.out.println("On suspend clicked for ID " + index);
        if(suspendController.suspend(results.get(index))){
            CreateDialog(pane, "Success", "The account has been suspended.");
            ((JFXButton) event.getSource()).setText("Suspended");
            ((JFXButton) event.getSource()).setDisable(true);
        }
    }

    @FXML
    void onUpdateBtnClicked(ActionEvent event) throws IOException {
        int index = Integer.parseInt(((Node) event.getSource()).getId());
        System.out.println("On update clicked for ID " + index);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateStaffAccountUI.fxml"));
        Scene scene = new Scene(loader.load());
        updateStaffAccountUI updateBoundary = loader.getController();
        updateBoundary.initFields(results.get(index));
        

        stage.setScene(scene);
        stage.show();
    }
}

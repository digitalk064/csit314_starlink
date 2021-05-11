package Starlink.views.healthStaff;

import Starlink.Starlink;
import Starlink.controllers.admin.PublicAccSuspendController;
import Starlink.controllers.admin.SearchPublicAccController;
import Starlink.controllers.healthStaff.GenerateVaxCertController;
import Starlink.entities.PublicUser;
import Starlink.entities.User;
import Starlink.views.CommonUI;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import java.util.List;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.scene.control.Label;
import Starlink.Starlink;
import Starlink.controllers.admin.PublicAccSuspendController;
import Starlink.controllers.admin.SearchPublicAccController;
import Starlink.controllers.healthStaff.GenerateVaxCertController;
import Starlink.entities.PublicUser;
import Starlink.views.CommonUI;
import javafx.concurrent.Task;
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

import java.util.*;


public class HealthStaffHomepageUI extends CommonUI{

    User user;

    SearchPublicAccController searchController;
    GenerateVaxCertController vaxController;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField inputtextfield;

   //@FXML
    //private JFXComboBox<?> searchByDropdown;

    @FXML
    private JFXButton SearchButton;

    @FXML
    private JFXButton logoutButton;

    //@FXML
   // private JFXListView<?> resultDisplayList;

    
    @FXML
    private JFXListView<Pane> resultDisplayList;

    @FXML
    private Text header;

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
    protected void initialize()// Called when the view is loaded
    {
        if(isRowItem)
            return;
        super.initialize();
        // Get the logged in user
        user = (User) stage.getUserData();
        header.setText(String.format("Hello, %s. You are a %s", user.getID(), user.getUserType()));
        searchController = new SearchPublicAccController();
        vaxController = new GenerateVaxCertController();
        searchByDropdown.getItems().add("ID number");
        searchByDropdown.getItems().add("Name");
        searchByDropdown.setValue("ID number");
        try{
            onSearchClicked(null);
        }catch(Exception e)
        {
            System.out.println("Error in onSearchClicked:");
            e.printStackTrace();
        }
    }


    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }

    List<PublicUser> results;

    @FXML
    void onSearchClicked(ActionEvent event) throws Exception {
        if(searchByDropdown.getValue().equals("ID number"))
            results = searchController.validateByID(inputtextfield.getText());
        else
            results = searchController.validateByName(inputtextfield.getText());
        System.out.println("Results count: " + results.size());
        DisplayResult();
    }

    void DisplayResult() throws Exception
    {
        resultDisplayList.getItems().clear(); 
        resultDisplayList.getItems().add(new Pane(new Label("Loading results...")));
        
        //Load result asynchronously
        Task listLoader = new Task<List<Pane>>() {
            {
                setOnSucceeded(workerStateEvent -> {
                    resultDisplayList.getItems().setAll(getValue());
                    System.out.println("Finished async loading");
                });

                setOnFailed(workerStateEvent -> getException().printStackTrace());
            }

            @Override
            protected List<Pane> call() throws Exception {
                final List<Pane> resultRows = new LinkedList<>();
                for(int i = 0; i < results.size(); i++){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Starlink/views/healthStaff/misc/searchResultRowTemplateHS.fxml"));
                    loader.setController(HealthStaffHomepageUI.this);
                    ((HealthStaffHomepageUI)loader.getController()).setIsRowItem();
                    Pane resultRow =  loader.load();
                    Label label = (Label)resultRow.lookup("#resultRowLabel");
                    label.setText(String.format("%-10s%-30s", results.get(i).getIDNum(), results.get(i).getName()));
                    JFXToggleButton vaxToggle = (JFXToggleButton)resultRow.lookup("#vaccinatedButton");
                    if(results.get(i).getVaxStatus())
                    {
                        vaxToggle.setSelected(true);
                        vaxToggle.setDisable(true);
                    }
                    ((JFXToggleButton)resultRow.lookup("#vaccinatedButton")).setId(String.valueOf(i));
                    resultRows.add(resultRow);
                    
                }
                return resultRows;
            }
        };

        Thread loadingThread = new Thread(listLoader, "list-loader");
        loadingThread.setDaemon(true);
        loadingThread.start();
    }

    @FXML
    void onChangeVaccinationStatus(ActionEvent event) throws Exception{
        int index = Integer.parseInt(((Node) event.getSource()).getId());
        boolean newStatus = ((JFXToggleButton)event.getSource()).isSelected();
        System.out.println("On vaccination clicked for ID " + index + " to " + newStatus);
        if(vaxController.setVaccinationStatus(results.get(index), newStatus))
        {
            CreateDialog(pane, "Success", "Successfully generated a vaccine certificate for the user.");
        }
        ((JFXToggleButton)event.getSource()).setDisable(true);
    }

    @FXML
    void onChangeInfectiontatus(ActionEvent event) {

    }



}

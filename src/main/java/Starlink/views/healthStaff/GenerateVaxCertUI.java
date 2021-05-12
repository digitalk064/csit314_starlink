package Starlink.views.healthStaff;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

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

// Merged with homepage
// Unused but kept for references
public class GenerateVaxCertUI extends CommonUI {

    SearchPublicAccController searchController;
    GenerateVaxCertController vaxController;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton SearchButton;

    @FXML
    private JFXButton CreatePublicUserAccountButton;

    @FXML
    private JFXButton BacktoHomepageButton;

    @FXML
    private JFXComboBox<?> userid;

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
    protected void initialize()// Called when the view is loaded
    {
        if(isRowItem)
            return;
        super.initialize();
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
    void onBacktoHomepageClicked(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("homepage_HealthStaff.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
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
                    loader.setController(GenerateVaxCertUI.this);
                    ((GenerateVaxCertUI)loader.getController()).setIsRowItem();
                    Pane resultRow =  loader.load();
                    Label label = (Label)resultRow.lookup("#resultRowLabel");
                    label.setText(String.format("%-10s%-30s", results.get(i).getIDNum(), results.get(i).getName()));
                    ((JFXToggleButton)resultRow.lookup("#vaccinatedButton")).setSelected(results.get(i).getVaxStatus());
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
            CreateDialog(pane, "Success", "The user's vaccination status has been set to " + newStatus + ".");
        }
    }

  
}






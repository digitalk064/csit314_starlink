package Starlink.views.publicUser;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import Starlink.controllers.publicUser.viewLocationHistoryController;
import Starlink.entities.LocHistory;
import Starlink.entities.PublicUser;
import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class LocationPublicUserUI extends CommonUI{

    PublicUser user;

    viewLocationHistoryController controller;

    @FXML
    private JFXListView<Pane> resultDisplayList;

    @FXML
    private JFXButton BackButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton logoutButton;

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
        user = (PublicUser) stage.getUserData();
        controller = new viewLocationHistoryController();
        try{
            GetAllRecords();
        }catch(Exception e)
        {
            System.out.println("Error while retrieving location history:");
            e.printStackTrace();
        }
    }

    List<LocHistory> results;
    void GetAllRecords() throws Exception
    {
        results = controller.getLocationRecords(user);
        DisplayHistory();
    }

    void DisplayHistory() throws Exception
    {
        resultDisplayList.getItems().clear(); 
        
        for(int i = 0; i < results.size(); i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Starlink/views/publicUser/misc/LocationRowTemplatePU.fxml"));
            loader.setController(LocationPublicUserUI.this);
            ((LocationPublicUserUI)loader.getController()).setIsRowItem();
            Pane resultRow =  loader.load();
            ((Label)resultRow.lookup("#resultRowLocLabel")).setText(results.get(i).getAddress());
            ((Label)resultRow.lookup("#resultRowTimeLabel")).setText(results.get(i).getCheckIn());
            resultDisplayList.getItems().add(resultRow);
        }
    }

    @FXML
    void onBackClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homepage_publicUser.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void onHomeClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("homepage_publicUser.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws Exception {
        Logout();
    }

}

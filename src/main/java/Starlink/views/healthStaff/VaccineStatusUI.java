package Starlink.views.healthStaff;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class VaccineStatusUI extends CommonUI{

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField inputtextfield;

    @FXML
    private JFXComboBox<?> searchByDropdown;

    @FXML
    private JFXButton SearchButton;

    @FXML
    private JFXButton BacktoHomepageButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXListView<?> resultDisplayList;
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

    @FXML
    void onSearchClicked(ActionEvent event) {
        

    }


}

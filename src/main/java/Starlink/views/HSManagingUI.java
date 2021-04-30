package Starlink.views;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HSManagingUI {

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton SuspendEditHealthStaffAccountButton;

    @FXML
    private JFXButton CreateHealthStaffAccountButton;

    @FXML
    private JFXButton BacktoHomepageButton;

    @FXML
    void SuspendEditHealthStaffAccountClicked(ActionEvent event) {

    }

    @FXML
    void onBacktoHomepageClicked(ActionEvent event) {

    }

    @FXML
    void onCreateHealthStaffAccountClicked(ActionEvent event)  {

    }

    void onLogoutClicked(ActionEvent event) throws Exception
    {
        Logout(event);
    }

    void Logout(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

}

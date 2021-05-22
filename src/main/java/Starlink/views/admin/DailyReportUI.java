package Starlink.views.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXButton;

import Starlink.controllers.admin.DailyReportController;
import Starlink.entities.DailyReport;
import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class DailyReportUI extends CommonUI {

    DailyReportController controller;

    @FXML
    private JFXButton BacktoHomepageButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private Label dateLabel;

    @FXML
    private Label casesLabel;

    @FXML
    private Label vaxLabel;

    @FXML
    protected void initialize()// Called when the view is loaded
    {
        super.initialize();

        controller = new DailyReportController();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDate now = LocalDate.now();  

        dateLabel.setText(now.toString());
        
        try{
            DailyReport report = controller.generateReport();
            DisplayReport(report);
        }
        catch(Exception e)
        {
            System.out.println("Error generating daily report.");
            e.printStackTrace();
        }

    }

    void DisplayReport(DailyReport report)
    {
        casesLabel.setText(String.valueOf(report.getTotalInfections()));
        vaxLabel.setText(String.valueOf(report.getTotalVaccinations()));
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

}

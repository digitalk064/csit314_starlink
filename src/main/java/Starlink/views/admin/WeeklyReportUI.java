package Starlink.views.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXButton;

import Starlink.controllers.admin.WeeklyReportController;
import Starlink.entities.WeeklyReport;
import Starlink.views.CommonUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class WeeklyReportUI extends CommonUI {

    WeeklyReportController controller;

    @FXML
    private JFXButton BacktoHomepageButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private Label fromDateLabel;

    @FXML
    private Label toDateLabel;

    @FXML
    private Label totalCasesLabel;

    @FXML
    private Label totalVaxLabel;

    @FXML
    private Label avgCasesLabel;

    @FXML
    private Label avgVaxLabel;

    @FXML
    protected void initialize()// Called when the view is loaded
    {
        super.initialize();

        controller = new WeeklyReportController();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDate now = LocalDate.now();  
        
        try{
            WeeklyReport report = controller.generateReport();
            DisplayReport(report);
        }
        catch(Exception e)
        {
            System.out.println("Error generating daily report.");
            e.printStackTrace();
        }

    }

    void DisplayReport(WeeklyReport report)
    {
        fromDateLabel.setText(report.getStartDate());
        toDateLabel.setText(report.getEndDate());
        totalCasesLabel.setText(String.valueOf(report.getTotalInfections()));
        totalVaxLabel.setText(String.valueOf(report.getTotalVaccinations()));
        avgCasesLabel.setText(String.valueOf(report.getAvgInfections()));
        avgVaxLabel.setText(String.valueOf(report.getavgVaccinations()));
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

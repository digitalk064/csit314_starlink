package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDate;    

public class WeeklyReport {
    private String startDate;
    private String endDate;
    private int totalInfections;
    private double avgInfections;
    private int totalVaccinations;
    private double avgVaccinations;

    public WeeklyReport(){};

    public WeeklyReport(String endDate)
    {
        this.endDate = endDate;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDate date = LocalDate.parse(endDate, dtf).minusDays(7);
        startDate = dtf.format(date);
    }

    public void generateReport() throws Exception
    {
        try
        {
            totalInfections = SQLHelper.selectStatement(String.format("select count(*) from publiUser where infectionStatus = %d and infectionTime >= '%s' " +
            "and infectionTime < '%s'",1, startDate, endDate)).getInt("count");

            avgInfections = totalInfections/7;

            totalVaccinations = SQLHelper.selectStatement(String.format("select count(*) from publiUser where vaxStatus = %d and vaxTime >= '%s' and vaxTime < '%s'", 
            1, startDate, endDate)).getInt("count");

            avgVaccinations = totalVaccinations/7;            
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
}

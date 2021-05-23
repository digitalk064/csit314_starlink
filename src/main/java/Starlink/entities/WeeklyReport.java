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

    public String getStartDate()
    {
        return startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public int getTotalInfections()
    {
        return totalInfections;
    }

    public int getTotalVaccinations()
    {
        return totalVaccinations;
    }

    public double getAvgInfections()
    {
        return avgInfections;
    }

    public double getavgVaccinations()
    {
        return avgVaccinations;
    }
    
    public WeeklyReport(){};

    public WeeklyReport(String endDate)
    {
        //The end date is supplied (which should be today's date)
        //and the start date is calculated by subtracting 7 days from it
        this.endDate = endDate;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDate date = LocalDate.parse(endDate, dtf).minusDays(7);
        startDate = dtf.format(date);
    }

    public void generateReport() throws Exception
    {
        try
        {
            //Count all the users set to infected on the specificied date range, do not include the end date because end date is set to today
            //and today might not have finished collecting data yet which could lead to inaccurate data
            totalInfections = SQLHelper.selectStatement(String.format("select count(*) as count from publicUser where infectionStatus = %d and infectionTime >= '%s' " +
            "and infectionTime < '%s'",1, startDate, endDate)).getInt("count");

            avgInfections = totalInfections/7;

            totalVaccinations = SQLHelper.selectStatement(String.format("select count(*) as count from publicUser where vaxStatus = %d and vaxTime >= '%s' and vaxTime < '%s'", 
            1, startDate, endDate)).getInt("count");

            avgVaccinations = totalVaccinations/7;            
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
}

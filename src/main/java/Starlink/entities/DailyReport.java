package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDate; 

public class DailyReport {
    private String date;
    private int totalInfections;
    private int totalVaccinations;

    public int getTotalInfections()
    {
        return totalInfections;
    }

    public int getTotalVaccinations()
    {
        return totalVaccinations;
    }

    public DailyReport(){};

    public DailyReport(String date)
    {
        this.date = date;
    }

    public void generateReport() throws Exception
    {
        try
        {
            totalInfections = SQLHelper.selectStatement(String.format("select count(*) as count from publicUser where infectionStatus = %d and infectionTime >= '%s 00:00:00' and infectionTime <= '%s 23:59:59'"
            ,1, date, date)).getInt("count");

            totalVaccinations = SQLHelper.selectStatement(String.format("select count(*) as count from publicUser where vaxStatus = %d and vaxTime >= '%s 00:00:00' and vaxTime <= '%s 23:59:59'", 
            1, date, date)).getInt("count");          
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

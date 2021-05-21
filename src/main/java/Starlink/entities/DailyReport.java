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

    public DailyReport(){};

    public DailyReport(String date)
    {
        this.date = date;
    }

    public void generateReport() throws Exception
    {
        try
        {
            totalInfections = SQLHelper.selectStatement(String.format("select count(*) from publiUser where infectionStatus = %d and infectionTime = '%s' "
            ,1, date)).getInt("count");

            totalVaccinations = SQLHelper.selectStatement(String.format("select count(*) from publiUser where vaxStatus = %d and vaxTime = '%s'", 
            1, date)).getInt("count");          
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

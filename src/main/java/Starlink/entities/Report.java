package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;    

enum ReportType{
    Daily,
    Weekly
}

public class Report {
    //Type variable
    private ReportType type;

    //Variables for daily report
    private String date;
    private int totalInfections; //Shared
    private int totalVaccinations; //Shared
    private List<String> locations; //Shared

    //Variables for weekly report
    private String startDate;
    private String endDate;
    private double avgInfections;
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

    public List<String> getLocations()
    {
        return locations;
    }
    
    public Report(){};

    public Report(String date)
    {
        this.date = date;
        //For the weekly report, the input date would also be the end date
        //and the start date is calculated by subtracting 7 days from it
        this.endDate = date;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDate _startDate = LocalDate.parse(endDate, dtf).minusDays(7);
        startDate = dtf.format(_startDate);
        locations = new ArrayList<String>();
    }

    public Report generateDailyReport() throws Exception
    {
        try
        {
            type = ReportType.Daily;
            //Start counting number of infected by first setting count to 0
            totalInfections = 0;
            //Get all the IDNums of the users who were infected in the day
            ResultSet IDNums = SQLHelper.selectStatement(String.format("select IDNum from publicUser where infectionStatus = %d and infectionTime >= '%s 00:00:00' and infectionTime <= '%s 23:59:59'"
            ,1, date, date));
            while(IDNums.next())
            {
                //Increment count by 1
                totalInfections++;
                String IDNum = IDNums.getString("IDNum");
                //Get the distinct addresses of the businesses the public user has visited
                ResultSet addresses = SQLHelper.selectStatement(
                    String.format(
                    "SELECT distinct address FROM business join locationHistory on business.businessID = locationHistory.businessID join publicUser on locationHistory.IDNum = publicUser.IDNum where locationHistory.IDNum = '%s'",
                    IDNum));
                //For each address
                while(addresses.next())
                {
                    //If the address is not already recorded in the locations list, add it
                    if(!locations.contains(addresses.getString("address")))
                        locations.add(addresses.getString("address"));
                }
            }
            totalVaccinations = SQLHelper.selectStatement(String.format("select count(*) as count from publicUser where vaxStatus = %d and vaxTime >= '%s 00:00:00' and vaxTime <= '%s 23:59:59'", 
            1, date, date)).getInt("count");          
            
            return this;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public Report generateWeeklyReport() throws Exception
    {
        try
        {
            type = ReportType.Weekly;
            //Start counting number of infected by first setting count to 0
            totalInfections = 0;
            //Get all the IDNums of the users who were infected on the specificied date range, do not include the end date because end date is set to today
            //and today might not have finished collecting data yet which could lead to inaccurate data
            ResultSet IDNums = SQLHelper.selectStatement(String.format("select IDNum from publicUser where infectionStatus = %d and infectionTime >= '%s' " +
            "and infectionTime < '%s'",1, startDate, endDate));
            while(IDNums.next())
            {
                //Increment count by 1
                totalInfections++;
                String IDNum = IDNums.getString("IDNum");
                //Get the distinct addresses of the businesses the public user has visited
                ResultSet addresses = SQLHelper.selectStatement(
                    String.format(
                    "SELECT distinct address FROM business join locationHistory on business.businessID = locationHistory.businessID join publicUser on locationHistory.IDNum = publicUser.IDNum where locationHistory.IDNum = '%s'",
                    IDNum));
                //For each address
                while(addresses.next())
                {
                    //If the address is not already recorded in the locations list, add it
                    if(!locations.contains(addresses.getString("address")))
                        locations.add(addresses.getString("address"));
                }
            }

            avgInfections = totalInfections/7f;

            totalVaccinations = SQLHelper.selectStatement(String.format("select count(*) as count from publicUser where vaxStatus = %d and vaxTime >= '%s' and vaxTime < '%s'", 
            1, startDate, endDate)).getInt("count");

            avgVaccinations = totalVaccinations/7f;

            return this;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
}

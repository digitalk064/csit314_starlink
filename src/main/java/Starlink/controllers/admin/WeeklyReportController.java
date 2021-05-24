package Starlink.controllers.admin;

import java.time.LocalDate;

import Starlink.entities.Report;

public class WeeklyReportController {
    
    public Report generateWeeklyReport() throws Exception
    {
        try{
            LocalDate now = LocalDate.now();  
            Report report = new Report(now.toString());
            report.generateWeeklyReport();
            return report;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

}

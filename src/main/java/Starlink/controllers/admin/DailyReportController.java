package Starlink.controllers.admin;

import java.time.LocalDate;

import Starlink.entities.Report;

public class DailyReportController {
    
    public Report generateDailyReport() throws Exception
    {
        try{
            LocalDate now = LocalDate.now();  
            Report report = new Report(now.toString());
            report.generateDailyReport();
            return report;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

}

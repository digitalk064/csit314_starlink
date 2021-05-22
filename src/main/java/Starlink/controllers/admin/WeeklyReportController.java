package Starlink.controllers.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Starlink.entities.WeeklyReport;

public class WeeklyReportController {
    
    public WeeklyReport generateReport() throws Exception
    {
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDate now = LocalDate.now();  
            WeeklyReport report = new WeeklyReport(now.toString());
            report.generateReport();
            return report;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

}

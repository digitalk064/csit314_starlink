package Starlink.controllers.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Starlink.entities.DailyReport;

public class DailyReportController {
    
    public DailyReport generateReport() throws Exception
    {
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDate now = LocalDate.now();  
            DailyReport report = new DailyReport(now.toString());
            report.generateReport();
            return report;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

}

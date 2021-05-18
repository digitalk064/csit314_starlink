package Starlink.controllers.healthStaff;

import java.util.List;

import Starlink.entities.Alert;
import Starlink.entities.LocHistory;

public class AlertPublicUserController {
    public boolean generateContactAlerts(List<LocHistory> contactRecords) throws Exception
    {
        try{
            for(LocHistory contactRecord : contactRecords)
            {
                int userid = contactRecord.getPublicUserID();
                if(userid == -1) //-1 means the location record belongs to the target/infected user, so ignore it
                    continue;
                String location = contactRecord.getAddress();
                String time = contactRecord.getCheckIn();
                Alert alert = new Alert();
                String message = 
                String.format("Dear user,\nOur system has recently determined that you were in contact with at least one person who was found to be infected at the following location: %s on %s",
                location, time);
                message += "\nPlease take necessary measures.\nThank you.";
                alert.generateAlert(userid, message);
            }
            return true;
        }catch(Exception e)
        {
            throw e;
        }
    }

    public boolean generateInfectedAlert(int userid) throws Exception
    {
        try{
            Alert alert = new Alert();
            String message = "Dear user,\nYou have been deemed infected after testing.";
            message += "\nPlease take necessary measures.\nThank you.";
            alert.generateAlert(userid, message);
        
        return true;
        }catch(Exception e)
        {
            throw e;
        }
    }
}

package Starlink.controllers.publicUser;

import java.util.List;

import Starlink.entities.Alert;
import Starlink.entities.PublicUser;

public class PublicUserNotificationController {
    public List<Alert> getAlerts(PublicUser publicuser) throws Exception
    {
        try{
            Alert find = new Alert();
            List<Alert> results = find.getAlerts(publicuser.getID());
            return results;
        }catch(Exception e)
        {
            throw e;
        }
    }

    //After the user has seen the alert, delete it
    public boolean deleteAlert(Alert alert) throws Exception
    {
        try{
            alert.deleteAlert();
            return true;
        }catch(Exception e)
        {
            throw e;
        }
    }
}

package Starlink.controllers.publicUser;

import java.util.List;

import Starlink.entities.Alert;
import Starlink.entities.Business;

public class BusinessNotificationController {
    public List<Alert> getAlerts(Business business) throws Exception
    {
        try{
            Alert find = new Alert();
            List<Alert> results = find.getAlerts(business.getID());
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

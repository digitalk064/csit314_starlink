package Starlink.controllers.healthStaff;

import java.util.List;

import Starlink.entities.Alert;

public class AlertBusinessController {
    public boolean generateAlert(List<Integer> ids) throws Exception
    {
        try{
            for(int i = 0; i < ids.size(); i++)
            {
                Alert alert = new Alert();
                String message = "Dear business owner,\nOur system has recently determined that a person infected with Covid-19 has been inside your location.";
                message += "\nPlease take necessary measures.\nThank you.";
                alert.generateAlert(ids.get(i), message);
            }
            return true;
        }catch(Exception e)
        {
            throw e;
        }
    }
}

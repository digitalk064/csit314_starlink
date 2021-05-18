package Starlink.controllers.healthStaff;

import java.util.List;

import Starlink.entities.Alert;

public class AlertBusinessController {
    public boolean generateAlert(List<Integer> businesses, List<String> times) throws Exception
    {
        try{
            for(int i = 0; i < businesses.size(); i++)
            {
                Alert alert = new Alert();
                String message = "Dear business owner,\nOur system has recently determined that a person infected with Covid-19 has last been inside your location on " + times.get(i) + ".";
                message += "\nPlease take necessary measures.\nThank you.";
                alert.generateAlert(businesses.get(i), message);
            }
            return true;
        }catch(Exception e)
        {
            throw e;
        }
    }
}

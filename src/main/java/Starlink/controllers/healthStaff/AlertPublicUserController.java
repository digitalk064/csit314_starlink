package Starlink.controllers.healthStaff;

import java.util.List;

import Starlink.entities.Alert;

public class AlertPublicUserController {
    public boolean generateAlert(List<Integer> IDs) throws Exception
    {
        try{
            for(int i = 0; i < IDs.size(); i++)
            {
                Alert alert = new Alert();
                String message = "Dear user,\nOur system has recently determined that you were in contact with a person who was found to be infected.";/*at one of the following locations: ";
                /*
                for(String loc : locations)
                    message += loc + ", ";
                */
                message += "\nPlease take necessary measures.\nThank you.";
                alert.generateAlert(IDs.get(i), message);
            }
            return true;
        }catch(Exception e)
        {
            throw e;
        }
    }
}

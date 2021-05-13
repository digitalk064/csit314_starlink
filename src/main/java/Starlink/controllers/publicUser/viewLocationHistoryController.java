package Starlink.controllers.publicUser;

import java.util.List;

import Starlink.entities.LocHistory;
import Starlink.entities.PublicUser;


public class viewLocationHistoryController {
    public List<LocHistory> getLocationRecords(PublicUser publicuser) throws Exception
    {
        try{
            LocHistory find = new LocHistory();
            List<LocHistory> results = find.retrieveRecords(publicuser.getIDNum());
            return results;
        }catch(Exception e)
        {
            throw e;
        }
    }
}

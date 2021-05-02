package Starlink.controllers.admin;

import java.util.List;

import Starlink.entities.HealthStaff;

public class SearchHealthStaffAccController {
    public List<HealthStaff> validateByID(String search_string) throws Exception
    {
        try{
            HealthStaff search = new HealthStaff();
            List<HealthStaff> results = search.searchByID(search_string);
            return results;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public List<HealthStaff> validateByName(String search_string) throws Exception
    {
        try{
            HealthStaff search = new HealthStaff();
            List<HealthStaff> results = search.searchByName(search_string);
            return results;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

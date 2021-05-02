package Starlink.controllers.admin;

import java.util.List;

import Starlink.entities.Business;

public class SearchBusinessAccController {
    public List<Business> validateByID(String search_string) throws Exception
    {
        try{
            Business search = new Business();
            List<Business> results = search.searchByID(search_string);
            return results;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public List<Business> validateByName(String search_string) throws Exception
    {
        try{
            Business search = new Business();
            List<Business> results = search.searchByName(search_string);
            return results;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

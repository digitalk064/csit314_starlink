package Starlink.controllers.admin;

import java.util.List;

import Starlink.entities.PublicUser;

public class SearchPublicAccController {
    public List<PublicUser> validateByID(String search_string) throws Exception
    {
        try{
            PublicUser search = new PublicUser();
            List<PublicUser> results = search.searchByID(search_string);
            return results;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public List<PublicUser> validateByName(String search_string) throws Exception
    {
        try{
            PublicUser search = new PublicUser();
            List<PublicUser> results = search.searchByName(search_string);
            return results;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

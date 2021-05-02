package Starlink.controllers.admin;

import Starlink.entities.Business;

public class createBusinessController {
    public boolean verifyInput(String username, String password, String email, String businessID, String name, String address) throws Exception
    {
        try{
            Business create = new Business();
            //Check if all fields are entered
            if(username.isBlank() || password.isBlank() || email.isBlank() || businessID.isBlank() || name.isBlank() || address.isBlank())
                throw new Exception("Please fill in all the fields");
            if(create.createAccount(username, password, email, businessID, name, address))
                return true;
        }
        catch(Exception e)
        {
            throw e;
        }
        return true;
    }
}

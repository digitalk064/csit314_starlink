package Starlink.controllers.admin;

import Starlink.entities.PublicUser;

public class createPublicAccountController {
    public boolean verifyInput(String username, String password, String email, String idNUM, String name) throws Exception
    {
        try{
            PublicUser create = new PublicUser();
            //Check if all fields are entered
            if(username.isBlank() || password.isBlank() || email.isBlank() || idNUM.isBlank() || name.isBlank())
                throw new Exception("Please fill in all the fields");
            if(create.createAccount(username, password, email, idNUM, name))
                return true;
        }
        catch(Exception e)
        {
            throw e;
        }
        return true;
    }
}

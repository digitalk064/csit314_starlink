package Starlink.controllers.admin;

import Starlink.entities.PublicUser;

public class updatePublicAccountController {
    public boolean verifyInput(int userID, String username, String password, String email, String IDNum, String name) throws Exception
    {
        try{
            PublicUser update = new PublicUser();
            //Check if all fields are entered
            if(username.isBlank() || password.isBlank() || email.isBlank() || IDNum.isBlank() || name.isBlank())
                throw new Exception("Please fill in all the fields");
            if(update.updateAccount(userID, username, password, email, IDNum, name))
                return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        return true;
    }
}

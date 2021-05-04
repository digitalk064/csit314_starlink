package Starlink.controllers.admin;

import Starlink.entities.HealthStaff;

public class updateHealthStaffController {
    public boolean verifyInput(int userID, String username, String password, String email, String staffID, String name) throws Exception
    {
        try{
            HealthStaff update = new HealthStaff();
            //Check if all fields are entered
            if(username.isBlank() || password.isBlank() || email.isBlank() || staffID.isBlank() || name.isBlank())
                throw new Exception("Please fill in all the fields");
            if(update.updateAccount(userID, username, password, email, staffID, name))
                return true;
        }
        catch(Exception e)
        {
            throw e;
        }
        return true;
    }
}

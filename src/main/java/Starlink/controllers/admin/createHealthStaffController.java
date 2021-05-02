package Starlink.controllers.admin;

import Starlink.entities.HealthStaff;

public class createHealthStaffController {
    public boolean verifyInput(String username, String password, String email, String staffID, String name) throws Exception
    {
        try{
            HealthStaff create = new HealthStaff();
            //Check if all fields are entered
            if(username.isBlank() || password.isBlank() || email.isBlank() || staffID.isBlank() || name.isBlank())
                throw new Exception("Please fill in all the fields");
            if(create.createAccount(username, password, email, staffID, name))
                return true;
        }
        catch(Exception e)
        {
            throw e;
        }
        return true;
    }
}

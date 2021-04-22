package Starlink.controllers;

import Starlink.entities.User;

public class LoginController {

    public LoginController()
    {

    }

    public boolean validateLogin(String username, String password)
    {
        //First step: validate the fields
        System.out.println("validate " + username + " " + password);
        if(username.isBlank() || password.isBlank())
            return false;
        
        //Second step: contact the entity
        // Might not be correct here
        User user = new User();
        if(user.login(username, password))
            return true;
        else return false;
    }
}

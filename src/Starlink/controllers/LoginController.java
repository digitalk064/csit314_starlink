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
        //... Not sure how to do this yet

        //If passed, return true

        return true;
    }
}

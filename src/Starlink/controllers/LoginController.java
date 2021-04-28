package Starlink.controllers;

import Starlink.entities.User;

public class LoginController {

    public LoginController()
    {

    }

    public User validateLogin(String username, String password) throws Exception
    {
        //First step: validate the fields
        System.out.println("validate " + username + " " + password);
        if(username.isBlank() || password.isBlank())
            throw new Exception("Please fill out both the username and password fields.");
        
        //Second step: contact the entity
        try{
            User user = new User();
            user = user.login(username, password);
            return user;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

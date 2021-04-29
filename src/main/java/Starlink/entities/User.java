package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

//User entity
//This is the superclass for all the user types
public class User {
    //Fields
    private int userid;
    private String username;
    private String password;
    private String email;
    private UserType userType;

    public UserType getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getID() {
        return userid;
    }

    public User()
    {}

    public User(int userid, String username, String password, String email, UserType userType) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    
    public User login(String username, String password) throws Exception
    {
        ResultSet results = SQLHelper.selectStatement(String.format("select * from user where username = '%s' and password = '%s'", username, password));
        if(results.next()){ //If there are any rows returned at all we have succeeded
            //Save the userType and userid from the logged in account to the persistent variables
            if(results.getString("suspended").equals("yes"))
                throw new Exception("This account has been suspended.");
            UserType type = UserType.valueOf(results.getString("usertype")); //Temporarily removing space
            int id = results.getInt("userID");
            String _username = results.getString("username");
            String _password = results.getString("password");
            String _email = results.getString("email");
            System.out.println("Found login record: " + id + " " + type);
            //Create the persistent User entity for the logged in user
            User session;
            switch(type){
                case PublicUser:
                    session = new PublicUser(id, _username, _password, _email);
                    break;
                case HealthStaff:
                    session = new HealthStaff(id, _username, _password, _email);
                    break;
                case HealthOrganization:
                    session = new HealthOrganization(id, _username, _password, _email);
                    break;
                case Business:
                    session = new Business(id, _username, _password, _email);
                    break;
                default:
                    System.out.println("Unknown user type for " + id);
                    throw new Exception("Found user record with unknown user type!");
            }
            return session;
        }
        //If we reach this part then there is no row found, return false
        throw new Exception("Your username or password might be incorrect.");
    }
}

enum UserType{
    PublicUser,
    HealthStaff,
    HealthOrganization,
    Business
}
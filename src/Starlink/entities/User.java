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

    //Static variables acting as session information
    //Not sure yet
    public static User session;

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

    
    public boolean login(String username, String password)
    {
        // Put SQL code here
        try{
            ResultSet results = SQLHelper.selectStatement(String.format("select * from user where username = '%s' and password = '%s'", username, password));
            if(results.next()){ //If there are any rows returned at all we have succeeded
                //Save the userType and userid from the logged in account to the persistent variables
                UserType type = UserType.valueOf(results.getString("usertype").replaceAll("\\s","")); //Temporarily removing space
                int id = results.getInt("userID");
                String _username = results.getString("username");
                String _password = results.getString("password");
                String _email = results.getString("email");
                System.out.println("Found login record: " + id + " " + type);
                //Create the persistent User entity for the logged in user
                session = new User(id, _username, _password, _email, type);
                return true;
            }
            //If we reach this part then there is no row found, return false
            return false;
        }
        catch(Exception e) //Error
        {
            System.out.println("Error in login(): ");
            e.printStackTrace();
            Platform.exit();
            System.exit(-1);
            return false;
        }
    }
}

enum UserType{
    PublicUser,
    HealthStaff,
    HealthOrganization,
    Business
}
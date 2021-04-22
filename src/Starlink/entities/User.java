package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

//User entity
//This is the superclass for all the user types
public class User {
    //Static variables acting as session information
    //Not sure yet
    public static UserType session_userType;
    public static int session_userid;
    // Some other info like name?
    
    public boolean login(String username, String password)
    {
        // Put SQL code here
        try{
            ResultSet results = SQLHelper.selectStatement(String.format("select * from user where username = '%s' and password = '%s'", username, password));
            if(results.next()){ //If there are any rows returned at all we have succeeded
                //Save the userType and userid from the logged in account to the persistent variables
                session_userType = UserType.valueOf(results.getString("usertype").replaceAll("\\s","")); //Temporarily removing space
                session_userid = results.getInt("userID");
                System.out.println("Found login record: " + session_userid + " " + session_userType);
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
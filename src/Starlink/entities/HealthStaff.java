package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

public class HealthStaff extends User{
    private String staffID;
    private String name;

    //Constructors
    public HealthStaff() {};
    public HealthStaff(int userid, String username, String password, String email) throws Exception {
        super(userid, username, password, email, UserType.HealthStaff);
        ResultSet results = SQLHelper.selectStatement(String.format("select * from healthStaff where userid = %d", userid));
        if(results.next()){ //If there are any rows returned at all we have succeeded
            //Save the userType and userid from the logged in account to the persistent variables
            name = results.getString("name");
            staffID = results.getString("staffID");
            return;
        }
        throw new Exception("Cannot find health staff account with userid " + userid + " in healthStaff table!");
    }

    public HealthStaff(int userid, String username, String password, String email, String staffID, String name){
        super(userid, username, password, email, UserType.HealthStaff);
        this.staffID = staffID;
        this.name = name; 
    }

    public boolean createHealthStaffAccount(String username, String password, String email, String staffID, String name)
    {
        try{
            SQLHelper.updateStatement(String.format("insert into user (username, password, email, userType, suspended) values (%s, %s, %s, HealthStaff, no)",
            username, password, email));

            //get the userID
            int userID = SQLHelper.selectStatement(String.format("select userID from user where username = %s", username));

            SQLHelper.updateStatement(String.format("insert into healthStaff (staffID, name, userID) values (%s, %s, %d)",
            staffID, name, userID));
            return true;
        }
        catch(Exception e)
        {
            throw e;
            return false;
        }
    }

    public boolean updateHealthStaffAccount(int userID, String username, String password, String email, String staffID, String name)
    {
        try{
            SQLHelper.updateStatement(String.format("update user set username = %s, password = %s, email = %s where userID = %d",
            username, password, email, userID));

            SQLHelper.updateStatement(String.format("update healthStaff set staffID = %s, name = %s where userID = %d",
            staffID, name, userID));
            return true;
        }
        catch(Exception e)
        {
            throw e;
            return false;
        }
    }
}

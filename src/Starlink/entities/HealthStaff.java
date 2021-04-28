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

    public void createHealthStaffAccount()
    {

    }
}

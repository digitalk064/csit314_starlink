package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

public class HealthOrganization extends User{
    private String name;

    public String getName()
    {
        return name;
    }

    //Constructors
    public HealthOrganization() {};
    public HealthOrganization(int userid, String username, String password, String email) throws Exception {
        super(userid, username, password, email, UserType.HealthOrganization);
        //Automatically fill in remaining information
        ResultSet results = SQLHelper.selectStatement(String.format("select * from healthOrg where userid = %d", userid));
        if(results.next()){ //If there are any rows returned at all we have succeeded
            //Save the userType and userid from the logged in account to the persistent variables
            name = results.getString("name");
            return;
        }
        //If we reach this part then there is no row found, throw an error
        throw new Exception("Cannot find health org account with userid " + userid + " in healthOrg table!");
    }
    public HealthOrganization(int userid, String username, String password, String email, String name) {
        super(userid, username, password, email, UserType.HealthOrganization);
        this.name = name;
    }
}

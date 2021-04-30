package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

public class Business extends User{
    private String name;
    private String businessID;
    private String address;

    //Constructors
    public Business() {};
    public Business(int userid, String username, String password, String email) throws Exception {
        super(userid, username, password, email, UserType.Business);
        //Automatically fill in remaining information
        //Find the remaining information from the Business table
        ResultSet results = SQLHelper.selectStatement(String.format("select * from business where userid = %d", userid));
        if(results.next()){ //If there are any rows returned at all we have succeeded
            //Save the userType and userid from the logged in account to the persistent variables
            businessID = results.getString("businessID");
            name = results.getString("name");
            address = results.getString("address");
            return;
        }
        //If we reach this part then there is no row found, throw an error
        throw new Exception("Cannot find business account with userid " + userid + " in business table!");
    }

    public Business(int userid, String username, String password, String email, String businessID, String name, String address) {
        super(userid, username, password, email, UserType.Business);
        this.businessID = businessID;
        this.name = name;
        this.address = address;
    }

    public boolean createBusinessAccount(String username, String password, String email, String businessID, String name, String address) throws Exception
    {
        try{
            SQLHelper.updateStatement(String.format("insert into user (username, password, email, userType, suspended) values (%s, %s, %s, Business, no)",
            username, password, email));

            //get the userID
            int userID = SQLHelper.selectStatement(String.format("select userID from user where username = %s", username)).getInt("userID");

            SQLHelper.updateStatement(String.format("insert into business (businessID, name, address, userID) values (%s, %s, %s, %d)",
            businessID, name, address, userID));

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public boolean updateBusinessAccount(int userID, String username, String password, String email, String businessID, String name, String address) throws Exception
    {
        try{
            SQLHelper.updateStatement(String.format("update user set username = %s, password = %s, email = %s where userID = %d",
            username, password, email, userID));

            SQLHelper.updateStatement(String.format("update business set businessID = %s, name = %s, address = %s where userID = %d",
            businessID, name, address, userID));

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

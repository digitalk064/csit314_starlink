package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

import java.util.List; // import just the List interface
import java.util.ArrayList; // import just the ArrayList class

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

    public boolean createAccount(String username, String password, String email, String businessID, String name, String address) throws Exception
    {
        try{
            //Check if username already exists
            if(SQLHelper.selectStatement(String.format("select username from user where username = '%s'", username)).next())
                throw new Exception("Username is already taken.");
            //Check if businessID already exists
            if(SQLHelper.selectStatement(String.format("select businessID from business where businessID = '%s'", businessID)).next())
                throw new Exception("A business with this business ID already exists.");

            SQLHelper.updateStatement(String.format("insert into user (username, password, email, userType, suspended) values ('%s', '%s', '%s', 'Business', 'no')",
            username, password, email));

            //get the userID
            int userID = SQLHelper.selectStatement(String.format("select userID from user where username = '%s'", username)).getInt("userID");

            SQLHelper.updateStatement(String.format("insert into business (businessID, name, address, userID) values ('%s', '%s', '%s', %d)",
            businessID, name, address, userID));

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public boolean updateAccount(int userID, String username, String password, String email, String businessID, String name, String address) throws Exception
    {
        try{
            SQLHelper.updateStatement(String.format("update user set username = '%s', password = '%s', email = '%s' where userID = %d",
            username, password, email, userID));

            SQLHelper.updateStatement(String.format("update business set businessID = '%s', name = '%s', address = '%s' where userID = %d",
            businessID, name, address, userID));

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public List<Business> searchByName(String search_string) throws Exception
    {

        try{
            List <Business> records = new ArrayList <Business>();

            ResultSet results = SQLHelper.selectStatement(String.format("select * from user join business on " + 
            "user.userID = business.userID where name like '%%%s%%'", search_string));

            while(results.next()){
                //get the user info from each row
                int id = results.getInt("userID");
                String _username = results.getString("username");
                String _password = results.getString("password");
                String _email = results.getString("email");

                //initiate a HealthStaff object
                Business Biz = new Business(id, _username, _password, _email);

                //add object to list
                records.add(Biz);
            }

            return records;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public List<Business> searchByStaffID(String search_string) throws Exception
    {
        try{
            List <Business> records = new ArrayList <Business>();

            ResultSet results = SQLHelper.selectStatement(String.format("select * from user join business on " + 
            "user.userID = business.userID where businessID like '%%%s%%'", search_string));

            while(results.next()){
                //get the user info from each row
                int id = results.getInt("userID");
                String _username = results.getString("username");
                String _password = results.getString("password");
                String _email = results.getString("email");

                //initiate a HealthStaff object
                Business Biz = new Business(id, _username, _password, _email);

                //add object to list
                records.add(Biz);
            }

            return records;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

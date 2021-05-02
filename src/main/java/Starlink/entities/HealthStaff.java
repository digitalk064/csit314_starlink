package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

import java.util.List; // import just the List interface
import java.util.ArrayList; // import just the ArrayList class

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

    public boolean createAccount(String username, String password, String email, String staffID, String name) throws Exception
    {
        try{
            //Check if username already exists
            if(SQLHelper.selectStatement(String.format("select username from user where username = '%s'", username)).next())
                throw new Exception("Username is already taken.");
            //Check if staffID already exists
            if(SQLHelper.selectStatement(String.format("select staffID from healthStaff where staffID = '%s'", staffID)).next())
                throw new Exception("A staff with this staff ID already exists.");

            SQLHelper.updateStatement(String.format("insert into user (username, password, email, userType, suspended) values ('%s', '%s', '%s', 'HealthStaff', 'no')",
            username, password, email));

            //Get the userID of the newly created account
            int userID = SQLHelper.selectStatement(String.format("select userID from user where username = '%s'", username)).getInt("userID");
            //Insert into the healthStaff table
            SQLHelper.updateStatement(String.format("insert into healthStaff (staffID, name, userID) values ('%s', '%s', '%d')",
            staffID, name, userID));
            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public boolean updateAccount(int userID, String username, String password, String email, String staffID, String name) throws Exception
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
        }
    }

    public List<HealthStaff> searchStaffByName(String search_string) throws Exception
    {

        try{
            List <HealthStaff> records = new ArrayList <HealthStaff>();

            ResultSet results = SQLHelper.selectStatement(String.format("select * from user join healthStaff on" + 
            "user.userID = healthStaff.userID where name = %s", search_string));

            while(results.next()){
                //get the user info from each row
                int id = results.getInt("userID");
                String _username = results.getString("username");
                String _password = results.getString("password");
                String _email = results.getString("email");

                //initiate a HealthStaff object
                HealthStaff HS = new HealthStaff(id, _username, _password, _email);

                //add object to list
                records.add(HS);
            }

            return records;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public List<HealthStaff> searchByStaffID(String search_string) throws Exception
    {
        try{
            List <HealthStaff> records = new ArrayList <HealthStaff>();

            ResultSet results = SQLHelper.selectStatement(String.format("select * from user join healthStaff on" + 
            "user.userID = healthStaff.userID where staffID = %s", search_string));

            while(results.next()){
                //get the user info from each row
                int id = results.getInt("userID");
                String _username = results.getString("username");
                String _password = results.getString("password");
                String _email = results.getString("email");

                //initiate a HealthStaff object
                HealthStaff HS = new HealthStaff(id, _username, _password, _email);

                //add object to list
                records.add(HS);
            }

            return records;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

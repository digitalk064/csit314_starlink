package Starlink.entities;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Starlink.SQLHelper;
import javafx.application.Platform;

import java.util.List; // import just the List interface
import java.util.ArrayList; // import just the ArrayList class

public class PublicUser extends User{
    private String IDNum;
    private String name;
    private boolean vaxStatus;
    private String vaxTime;
    private boolean infectionStatus;
    private String infectionTime;

    //Due to huge amount of records, we are limiting the number of rows returned to avoid slow down when searching
    final int limit = 200;

    public String getIDNum()
    {
        return IDNum;
    }
    public String getName()
    {
        return name;
    }

    public boolean getVaxStatus()
    {
        return vaxStatus;
    }

    public String getVaxTime()
    {
        return vaxTime;
    }

    public boolean getInfectionStatus()
    {
        return infectionStatus;
    }

    public String getInfectionTime()
    {
        return infectionTime;
    }

    public boolean setVaxStatus(boolean vaxStatus) throws Exception{
        this.vaxStatus = vaxStatus;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        SQLHelper.updateStatement(String.format("update publicUser set vaxStatus = %d, vaxTime = '%s' where userID = '%s'",
            (vaxStatus ? 1 : 0), LocalDateTime.now().format(format), userid));
        return true;
        
    }

    public boolean setInfectionStatus(boolean infectionStatus) throws Exception{
        this.infectionStatus = infectionStatus;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        SQLHelper.updateStatement(String.format("update publicUser set infectionStatus = %d, infectionTime = '%s' where userID = '%s'",
            (infectionStatus ? 1 : 0), LocalDateTime.now().format(format), userid));
        return true;
        
    }

    //Constructors
    public PublicUser() {};
    public PublicUser(int userid, String username, String password, String email) throws Exception{
        super(userid, username, password, email, UserType.PublicUser);
        //Automatically fill in remaining information
        ResultSet results = SQLHelper.selectStatement(String.format("select * from publicUser where userid = %d", userid));
        if(results.next()){ //If there are any rows returned at all we have succeeded
            //Save the userType and userid from the logged in account to the persistent variables
            name = results.getString("name");
            IDNum = results.getString("IDNum");
            vaxStatus = results.getBoolean("vaxStatus");
            vaxTime = results.getString("vaxTime");
            infectionStatus = results.getBoolean("infectionStatus");
            infectionTime = results.getString("infectionTime");
            return;
        }
        throw new Exception("Cannot find public user account with userid " + userid + " in publicUser table!");
    }

    public PublicUser(int userid, String username, String password, String email, String IDNum, String name, Boolean vaxStatus, String vaxTime, Boolean infectionStatus, String infectionTime) {
        super(userid, username, password, email, UserType.PublicUser);
        this.IDNum = IDNum;
        this.name = name;
        this.vaxStatus = vaxStatus;
        this.vaxTime = vaxTime;
        this.infectionStatus = infectionStatus;
        this.infectionTime = infectionTime;
    }

    public boolean createAccount(String username, String password, String email, String IDNum, String name) throws Exception
    {
        try{
            //Check if username already exists
            if(SQLHelper.selectStatement(String.format("select username from user where username = '%s'", username)).next())
                throw new Exception("Username is already taken.");
            //Check if IDNum already exists
            if(SQLHelper.selectStatement(String.format("select IDNum from publicUser where IDNum = '%s'", IDNum)).next())
                throw new Exception("A public user with this identification number already exists.");

            SQLHelper.updateStatement(String.format("insert into user (username, password, email, userType, suspended) values ('%s', '%s', '%s', 'PublicUser', 'no')",
            username, password, email));

            //get the userID
            int userID = SQLHelper.selectStatement(String.format("select userID from user where username = '%s'", username)).getInt("userID");

            SQLHelper.updateStatement(String.format("insert into publicUser (IDNum, name, userID, vaxStatus, vaxTime, infectionStatus, infectionTime) values ('%s', '%s', %d, %d, NULL, %d, NULL)",
            IDNum, name, userID, 0, 0));

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public boolean updateAccount(int userID, String username, String password, String email, String IDNum, String name) throws Exception
    {
        try{
            //Check if userID actually exists
            if(!SQLHelper.selectStatement(String.format("select userID from user where userID = %d", userID)).next())
                throw new Exception("Trying to update a non-existent account! (ID: " + userID + ")");
            //Check if new username is already taken
            ResultSet results = SQLHelper.selectStatement(String.format("select userID from user where username = '%s'", username));
            while(results.next()){
                if(results.getInt("userID") != userID) //Make sure we're not against the account being updated
                    throw new Exception("Another account already uses this username!");
            }
            //Check if new IDNum is already taken
            results = SQLHelper.selectStatement(String.format("select userID from publicUser where IDNum = '%s'", IDNum));
            while(results.next()){
                if(results.getInt("userID") != userID) //Make sure we're not checking against the account being updated
                    throw new Exception("Another account already uses this identification number!");
            }
            
            SQLHelper.updateStatement(String.format("update user set username = '%s', password = '%s', email = '%s' where userID = %d",
            username, password, email, userID));

            SQLHelper.updateStatement(String.format("update publicUser set IDNum = '%s', name = '%s' where userID = %d",
            IDNum, name, userID));

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public List<PublicUser> searchByName(String search_string) throws Exception
    {

        try{
            List <PublicUser> records = new ArrayList <PublicUser>();

            ResultSet results = SQLHelper.selectStatement(String.format("select * from user join publicUser on " + 
            "user.userID = publicUser.userID where name like '%%%s%%' limit %d", search_string, limit));

            while(results.next()){
                //get the user info from each row
                int id = results.getInt("userID");
                String _username = results.getString("username");
                String _password = results.getString("password");
                String _email = results.getString("email");

                //initiate a HealthStaff object
                PublicUser PU = new PublicUser(id, _username, _password, _email);

                //add object to list
                records.add(PU);
            }

            return records;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public List<PublicUser> searchByID(String search_string) throws Exception
    {
        try{
            List <PublicUser> records = new ArrayList <PublicUser>();

            ResultSet results = SQLHelper.selectStatement(String.format("select * from user join publicUser on " + 
            "user.userID = publicUser.userID where IDNum like '%%%s%%' limit %d", search_string, limit));

            while(results.next()){
                //get the user info from each row
                int id = results.getInt("userID");
                String _username = results.getString("username");
                String _password = results.getString("password");
                String _email = results.getString("email");

                //initiate a HealthStaff object
                PublicUser PU = new PublicUser(id, _username, _password, _email);

                //add object to list
                records.add(PU);
            }

            return records;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

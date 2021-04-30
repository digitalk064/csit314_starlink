package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

public class PublicUser extends User{
    private String IDNum;
    private String name;

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
            return;
        }
        throw new Exception("Cannot find public user account with userid " + userid + " in publicUser table!");
    }

    public PublicUser(int userid, String username, String password, String email, String IDNum, String name) {
        super(userid, username, password, email, UserType.PublicUser);
        this.IDNum = IDNum;
        this.name = name;
    }

    public boolean createPublicUserAccount(String username, String password, String email, String IDNum, String name) throws Exception
    {
        try{
            SQLHelper.updateStatement(String.format("insert into user (username, password, email, userType, suspended) values (%s, %s, %s, PublicUser, no)",
            username, password, email));

            //get the userID
            int userID = SQLHelper.selectStatement(String.format("select userID from user where username = %s", username)).getInt("userID");

            SQLHelper.updateStatement(String.format("insert into publicUser (IDNum, name, userID) values (%s, %s, %d)",
            IDNum, name, userID));

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public boolean updatePublicUserAccount(int userID, String username, String password, String email, String IDNum, String name) throws Exception
    {
        try{
            SQLHelper.updateStatement(String.format("update user set username = %s, password = %s, email = %s where userID = %d",
            username, password, email, userID));

            SQLHelper.updateStatement(String.format("update publicUser set IDNum = %s, name = %s where userID = %d",
            IDNum, name, userID));

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

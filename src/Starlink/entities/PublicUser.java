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
}

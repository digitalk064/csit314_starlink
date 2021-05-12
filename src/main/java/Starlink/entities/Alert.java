package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

import java.util.List; // import just the List interface
import java.util.ArrayList; // import just the ArrayList class

public class Alert {
    private int userID;
    private String message;

    public int getUserID()
    {
        return userID;
    }
    public String getMessage()
    {
        return message;
    }
    public void setUserID(int userID)
    {
        this.userID = userID;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

    //Constructor
    public Alert(){};
    public Alert(int userID, String message)
    {
        this.userID = userID;
        this.message = message;
    }

    public boolean generateAlerts(int userID, String message) throws Exception
    {
        try{
            SQLHelper.updateStatement(String.format("insert into alerts (userID, message) values (%d, '%s')",
            userID, message));
            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public List<Alert> getAlerts(int userID) throws Exception
    {

        try{
            List <Alert> records = new ArrayList <Alert>();

            ResultSet results = SQLHelper.selectStatement(String.format("select message from alerts where userID = %d", userID));

            while(results.next()){
                //get the message from each row
                String msg = results.getString("message");

                //initiate an Alert object
                Alert AL = new Alert(userID, msg);

                //add object to list
                records.add(AL);
            }

            return records;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

}

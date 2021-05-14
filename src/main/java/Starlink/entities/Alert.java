package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

import java.util.List; // import just the List interface
import java.util.ArrayList; // import just the ArrayList class

public class Alert {
    private int alertID;
    private int userID;
    private String message;

    public int getAlertID()
    {
        return alertID;
    }
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
    public Alert(int alertID, int userID, String message)
    {
        this.alertID = alertID;
        this.userID = userID;
        this.message = message;
    }

    public boolean generateAlert(int userID, String message) throws Exception
    {
        try{
            this.userID = userID;
            this.message = message;
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

            ResultSet results = SQLHelper.selectStatement(String.format("select alertID, message from alerts where userID = %d", userID));

            while(results.next()){
                //get the message from each row
                int alertID = results.getInt("alertID"); 
                String msg = results.getString("message");

                //initiate an Alert object
                Alert AL = new Alert(alertID, userID, msg);

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

    //After the user has seen the alert, delete it
    public boolean deleteAlert() throws Exception
    {
        try{
            SQLHelper.updateStatement(String.format("delete from alerts where alertID = %d", alertID));
            return true;
        }catch(Exception e)
        {
            throw e;
        }
    }

}

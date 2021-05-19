package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

import java.util.List; // import just the List interface
import java.util.ArrayList; // import just the ArrayList class

public class LocHistory {
    //Attributes in schema
    private String IDNum;
    private String businessID;
    private String checkIn;
    private String checkOut;

    // Attributes that are not in the schema but will be
    // available from using SQL join operation
    private String businessAddress;
    private String userFullName;
    private int businessUserID; //(UserID from User superclass) Needed for alerting
    private int publicUserID; //(UserID from User superclass) Needed for alerting

    public String getIDNum()
    {
        return IDNum;
    }
    public String getBusinessID()
    {
        return businessID;
    }
    public String getCheckIn()
    {
        return checkIn;
    }
    public String getCheckOut()
    {
        return checkOut;
    }
    public String getAddress()
    {
        return businessAddress;
    }
    public String getUserName()
    {
        return userFullName;
    }
    public int getPublicUserID()
    {
        return publicUserID;
    }
    public int getBusinessUserID()
    {
        return businessUserID;
    }

    public void setIDNum(String IDNum)
    {
        this.IDNum = IDNum;
    }
    public void setBusinessID(String businessID)
    {
        this.businessID = businessID;
    }
    public void setCheckIn(String checkIn)
    {
        this.checkIn = checkIn;
    }
    public void setCheckOut(String checkOut)
    {
        this.checkOut = checkOut;
    }
    public void setAddress(String address)
    {
        this.businessAddress = address;
    }
    //Constructor
    public LocHistory(){}

    public LocHistory(String IDNum, String businessID, String checkIn, String checkOut)
    {
        this.IDNum = IDNum;
        this.businessID = businessID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public LocHistory(String IDNum, String businessID, String checkIn, String checkOut, String address)
    {
        this.IDNum = IDNum;
        this.businessID = businessID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.businessAddress = address;
    }

    public LocHistory(String IDNum, String businessID, String checkIn, String checkOut, String address, String userFullName, int publicUserID, int businessUserID)
    {
        this.IDNum = IDNum;
        this.businessID = businessID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.businessAddress = address;
        this.userFullName = userFullName;
        this.publicUserID = publicUserID;
        this.businessUserID = businessUserID;
    }

    //Retrieve records for the public user viewing his/her location history
    public List<LocHistory> retrieveRecords(String IDNum) throws Exception
    {
        try{
            List <LocHistory> records = new ArrayList <LocHistory>();

            ResultSet results = SQLHelper.selectStatement(String.format(
                "select * from locationHistory join business on locationHistory.businessID = business.businessID where IDNum = '%s' order by checkIn desc", IDNum));

            while(results.next()){
                //get the location details from each row
                String businessID = results.getString("businessID");
                String checkIn = results.getString("checkIn");
                String checkOut = results.getString("checkOut");
                String address = results.getString("address");
                
                //initiate a LocationHistory object
                LocHistory LH = new LocHistory(IDNum, businessID, checkIn, checkOut, address);

                //add object to list
                records.add(LH);
            }

            return records;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public List<LocHistory> traceContact(String IDNum) throws Exception
    {
        try{
            List <LocHistory> records = new ArrayList <LocHistory>();
            
            // First, get all the locations the target person has visited
            // We also get the business address, business user ID (for alerting later)
            ResultSet results = SQLHelper.selectStatement(String.format(
                "select locationHistory.IDNum, locationHistory.businessID, business.address, business.userID as businessuserid, checkIn, checkOut " +
                "from locationHistory join business on locationHistory.businessID = business.businessID where IDNum = '%s' order by checkIn desc, businessuserid", IDNum));

            while(results.next()){
                //get the location details from each row
                String businessID = results.getString("businessID");
                int businessUserID = results.getInt("businessuserid");
                String address = results.getString("address");

                //get checkin and checkout time of target public user
                String checkIn = results.getString("checkIn");
                String checkOut = results.getString("checkOut");
                
                // In the final list, each unique location will start with LocHistory of the target user (we do not need to store many details here)
                // put the publicUserID as -1 to mark it as such 
                // and subsequent records will contain the LocHistory of users who were in contact with the target user
                // at the location
                records.add(new LocHistory(IDNum, null, checkIn, null, address, null, -1, businessUserID));

                // Now find all other people (different IDNum) who have been in the same business place and were in contact
                // We do NOT select the cases where person B was not in contact (checked in later than person A's checkout or checked out earlier than person A's checkin)
                // The remaining cases will be when the two overlap (meaning there's contact)
                // We also need to join the publicUser and business tables to get the actual location name and the user's name. And the user ID of the user and business.
                /*
                System.out.println(String.format(
                    "SQL Statement: \n"+
                    "select locationHistory.IDNum, publicUser.name as userFullName, publicUser.userID as publicuserid, checkIn, checkOut " +
                    "from locationHistory join business on locationHistory.businessID = business.businessID join publicUser on locationHistory.IDNum = publicUser.IDNum where locationHistory.IDNum != '%s' and locationHistory.businessID = '%s' and not checkIn >= '%s' and not checkOut <= '%s' order by publicUser.userID", 
                    IDNum, businessID, checkOut, checkIn));
                */
                ResultSet PURecords = SQLHelper.selectStatement(String.format(
                    "select locationHistory.IDNum, publicUser.name as userFullName, publicUser.userID as publicuserid, checkIn, checkOut " +
                    "from locationHistory join business on locationHistory.businessID = business.businessID join publicUser on locationHistory.IDNum = publicUser.IDNum where locationHistory.IDNum != '%s' and locationHistory.businessID = '%s' and not checkIn >= '%s' and not checkOut <= '%s' order by checkIn desc", 
                    IDNum, businessID, checkOut, checkIn));

                while(PURecords.next()){
                    // get traced public user info
                    String _id = PURecords.getString("IDNum");
                    String userFullName = PURecords.getString("userFullName");

                    //get user ID of traced public user
                    int publicUserID = PURecords.getInt("publicuserid");

                    //get the checkin and checkout time of the traced public user
                    String _checkIn = PURecords.getString("checkIn");
                    String _checkOut = PURecords.getString("checkOut");
                    

                    //initiate a LocationHistory object
                    LocHistory LH = new LocHistory(_id, businessID, _checkIn, _checkOut, address, userFullName, publicUserID, businessUserID);

                    //add object to list
                    records.add(LH);
                }
            }

            return records;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}

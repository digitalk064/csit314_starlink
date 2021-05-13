package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

import java.util.List; // import just the List interface
import java.util.ArrayList; // import just the ArrayList class

public class LocHistory {
    private String IDNum;
    private String businessID;
    private String checkIn;
    private String checkOut;

    //Might not be allowed
    private String address;

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
        return address;
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
        this.address = address;
    }
    //Constructor
    public LocHistory(){}
    public LocHistory(String IDNum, String businessID, String checkIn, String checkOut, String address)
    {
        this.IDNum = IDNum;
        this.businessID = businessID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.address = address;
    }

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

            ResultSet results = SQLHelper.selectStatement(String.format("select * from locationHistory where IDNum = '%s'", IDNum));

            while(results.next()){
                //get the location details from each row
                String businessID = results.getString("businessID");
                String checkIn = results.getString("checkIn");
                String checkOut = results.getString("checkOut");

                ResultSet PURecords = SQLHelper.selectStatement(String.format("select * from locationHistory where businessID = '%s' and checkOut >= '%s' and checkOut <= '%s'", businessID, checkIn, checkOut));

                while(PURecords.next()){
                    //get public user IDNum
                    String _id = results.getString("IDNum");

                    //get the location details from each row
                    String _businessID = results.getString("businessID");
                    String _checkIn = results.getString("checkIn");
                    String _checkOut = results.getString("checkOut");
                    String address = results.getString("address");

                    //initiate a LocationHistory object
                    LocHistory LH = new LocHistory(_id, _businessID, _checkIn, _checkOut, address);

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

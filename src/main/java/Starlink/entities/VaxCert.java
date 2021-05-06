package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

public class VaxCert {
    String IDNum;
    String issuedByStaffID;
    String vaxName;
    String batchNo;
    String dateIssued;
    String patientName;  
    
    public String getIDNum()
    {
        return IDNum;
    }
    public String getIssuedByStaffID()
    {
        return issuedByStaffID;
    }
    public String getVaxName()
    {
        return vaxName;
    }
    public String getBatchNo()
    {
        return batchNo;
    }
    public String getDateIssued()
    {
        return dateIssued;
    }

    //Constructors
    public VaxCert() {};

    public VaxCert(String IDNum, String vaxName, String dateIssued, String issuedByStaffID, String batchNo) throws Exception {
        this.IDNum = IDNum;
        this.issuedByStaffID = issuedByStaffID;
        this.vaxName = vaxName;
        this.batchNo = batchNo;
        this.dateIssued = dateIssued;

        //Automatically fill in remaining information
        ResultSet results = SQLHelper.selectStatement(String.format("select name from publicUser where IDNum = %d", IDNum));
        if(results.next()){ //If there are any rows returned at all we have succeeded
            //Save the userType and userid from the logged in account to the persistent variables
            patientName = results.getString("name");
            return;
        }
        throw new Exception("Cannot find public user account with ID number " + IDNum + " in publicUser table!");
    };

    public VaxCert(String IDNum, String vaxName, String dateIssued, String issuedByStaffID, String batchNo, String patientName){
        this.IDNum = IDNum;
        this.issuedByStaffID = issuedByStaffID;
        this.vaxName = vaxName;
        this.batchNo = batchNo;
        this.dateIssued = dateIssued;
        this.patientName = patientName;
    };

    public boolean generateVaxCert(String IDNum, String vaxName, String dateIssued, String issuedByStaffID, String batchNo) throws Exception
    {
        try{
            //Check if vaccination certification already exists
            if(SQLHelper.selectStatement(String.format("select IDNum from vaxCert where IDNum = '%s'", IDNum)).next())
                throw new Exception("Vaccination certificate is already issued to public user with this identification number.");

            //generate the vaccination certificate
            SQLHelper.updateStatement(String.format("insert into vaxCert (IDNum, vaxName, dateIssued, issuedByStaffID, batchNo) values ('%s', '%s', '%s', '%s', '%s')",
            IDNum, vaxName, dateIssued, issuedByStaffID, batchNo));

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public VaxCert viewVaxCert(String IDNum) throws Exception
    {
        try{

            VaxCert VC = null;
            ResultSet results = SQLHelper.selectStatement(String.format("select * from vaxCert join publicUser on " + 
            "vaxCert.IDNum = publicUser.IDNum where IDNum = '%s'", IDNum));

            if(results.next()){
                //get the info from the row
                String vaccine = results.getString("vaxName");
                String date = results.getString("dateIssued");
                String staffID = results.getString("issuedByStaffID");
                String batch = results.getString("batchNo");
                String name = results.getString("name");

                //initiate a HealthStaff object
                VC = new VaxCert(IDNum, vaccine, date, staffID, batch, name);
            }

            return VC;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


}

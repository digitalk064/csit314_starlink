package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

public class HealthStaff extends User{
    //What attributes?

    //Constructors
    public HealthStaff() {};
    public HealthStaff(int userid, String username, String password, String email, UserType userType) {
        super(userid, username, password, email, userType);
    }

    public void createHealthStaffAccount()
    {

    }
}

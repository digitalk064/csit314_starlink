package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

public class HealthOrganization extends User{
    //What attributes?

    //Constructors
    public HealthOrganization() {};
    public HealthOrganization(int userid, String username, String password, String email, UserType userType) {
        super(userid, username, password, email, userType);
    }
}

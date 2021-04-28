package Starlink.entities;

import java.sql.*;
import Starlink.SQLHelper;
import javafx.application.Platform;

public class Business extends User{
    private String name;
    //Location later

    //Constructors
    public Business() {};
    public Business(int userid, String username, String password, String email, UserType userType) {
        super(userid, username, password, email, userType);
    }

    public void createBusinessAccount()
    {

    }
}

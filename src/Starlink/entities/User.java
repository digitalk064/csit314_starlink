package Starlink.entities;

//User entity
//This is the superclass for all the user types
public class User {
    //Static variables acting as session information
    //Not sure yet
    public static UserType userType;
    public static String username;
    
    public boolean login(String username, String password)
    {
        //...
        return true;
    }

}

enum UserType{
    PublicUser,
    HealthStaff,
    HealthOrg,
    Business
}
package Starlink;

import java.io.File;
import java.sql.*;

public class SQLHelper {
    private static Connection conn = null;

    public static void startDBConnection(String filename) throws Exception
    {
        File file = new File (filename); //Check if db file exists
        if(!file.exists())
        {
            System.out.println("The db file could not be found!");
            throw new Exception("The db file could not be found");
        }
        String url = "jdbc:sqlite:contactTracing.db";
        conn = DriverManager.getConnection(url);
        conn.setAutoCommit(false); // Don't need to write to disk every time we change the database
        System.out.println("Connected to SQLite db file");
    }

    public static Connection getConnection()
    {
        return conn;
    }

    public static void closeConnection() throws Exception
    {
        conn.commit();
        conn.close();
        System.out.println("Closed connection to SQLite db file");
    }
    
    //Select statements only
    public static ResultSet selectStatement(String statement) throws Exception
    {
        PreparedStatement stmt = conn.prepareStatement(statement);
        ResultSet results = stmt.executeQuery();
        stmt.closeOnCompletion(); //To auto close
        return results;
    }

    //Update, Insert, Delete statements only
    public static int updateStatement(String statement) throws Exception
    {
        PreparedStatement stmt = conn.prepareStatement(statement);
        int results = stmt.executeUpdate();
        stmt.closeOnCompletion(); // To auto close
        return results;
    }
}

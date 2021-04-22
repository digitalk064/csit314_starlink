package Starlink;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Starlink extends Application {

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Inside init() method! Perform necessary initializations here.");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Inside stop() method! Destroy resources. Perform Cleanup.");
    }

    public static void main(String[] args) {
        //Connect to the db file first
        try{
            SQLHelper.startDBConnection("contactTracing.db");
        }
        catch(Exception e)
        {
            System.out.println("Error in main(): Cannot connect to the db file");
            Platform.exit();
            System.exit(-1);
        }

        //Open the application window
        launch(args);        

        //Now close the connection to the db before shutting odwn
        //Connect to the db file first
        try{
            SQLHelper.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println("Error in main(): Cannot close connection to the db file");
        }

    }
}
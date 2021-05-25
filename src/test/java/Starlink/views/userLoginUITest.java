package Starlink.views;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import org.junit.jupiter.api.*;

import Starlink.SQLHelper;
import Starlink.Starlink;


public class userLoginUITest {
    userLoginUI boundary;
    //We must connect to the db file and start the javafx thread before testing can begin
    @BeforeAll
    public static void setUpApp() throws Exception{
        SQLHelper.startDBConnection("contactTracing.db");
        //Create javafx thread
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Starlink.launch(Starlink.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
        //The javafx thread takes time to initialize, so we have to wait
        Thread.sleep(1000);
    }

    @BeforeEach
    public void setUp() throws Exception{
        //Create boundary object
        this.boundary = new userLoginUI();
        //Initialize the UI elements
        boundary.usernameField = new JFXTextField();
        boundary.passwordField = new JFXPasswordField();
        boundary.rootPane = new StackPane();
        //Initialize the boundary
        boundary.initialize();
    }

    @Test
    public void testWrongLogin() throws Exception
    {
        boundary.usernameField.setText("Wrong");
        boundary.passwordField.setText("Wrong");
        try{
            boundary.onSubmit(null);
        }
        catch(Exception e)
        {
            fail("userLoginUI tried to throw exception in onSubmit() which can only happen when trying to go to homepage!");
        }
    }

    @Test
    public void testLoginHealthStaff() throws Exception
    {
        boundary.usernameField.setText("A");
        boundary.passwordField.setText("av");
        //We know when logging in the boundary will try to go to the homepage which will throw an error when running in test environment
        assertThrows(
            Exception.class,
            () -> boundary.onSubmit(null),
            "Expected exception as userLoginUI boundary tries to switch scene but got no exception!"
        ); 
    }

    @Test
    public void testLoginHealthOrg() throws Exception
    {
        boundary.usernameField.setText("B");
        boundary.passwordField.setText("bv");
        //We know when logging in the boundary will try to go to the homepage which will throw an error when running in test environment
        assertThrows(
            Exception.class,
            () -> boundary.onSubmit(null),
            "Expected exception as userLoginUI boundary tries to switch scene but got no exception!"
        ); 
    }

    @Test
    public void testLoginBusiness() throws Exception
    {
        boundary.usernameField.setText("C");
        boundary.passwordField.setText("cv");
        //We know when logging in the boundary will try to go to the homepage which will throw an error when running in test environment
        assertThrows(
            Exception.class,
            () -> boundary.onSubmit(null),
            "Expected exception as userLoginUI boundary tries to switch scene but got no exception!"
        ); 
    }

    @Test
    public void testLoginPublicUser() throws Exception
    {
        boundary.usernameField.setText("D");
        boundary.passwordField.setText("dv");
        //We know when logging in the boundary will try to go to the homepage which will throw an error when running in test environment
        assertThrows(
            Exception.class,
            () -> boundary.onSubmit(null),
            "Expected exception as userLoginUI boundary tries to switch scene but got no exception!"
        ); 
    }
}

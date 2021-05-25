package Starlink.views;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import Starlink.SQLHelper;
import Starlink.Starlink;

import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;


@ExtendWith(ApplicationExtension.class)
public class userLoginUITest extends ApplicationTest {
    @BeforeAll
    public static void setUpApp() throws Exception{
        SQLHelper.startDBConnection("contactTracing.db");
    }

    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("login.fxml"));
        Starlink.stage = stage;
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @Test
    public void testWrongLogin(FxRobot robot) throws Exception
    {
        ((JFXTextField)lookup("#usernameField").query()).setText("A");
        ((JFXPasswordField)lookup("#passwordField").query()).setText("av");
        robot.clickOn("#loginButton");
    }

}

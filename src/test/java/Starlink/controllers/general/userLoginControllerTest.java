package Starlink.controllers.general;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import Starlink.SQLHelper;
import Starlink.entities.Business;
import Starlink.entities.HealthOrganization;
import Starlink.entities.HealthStaff;
import Starlink.entities.PublicUser;

public class userLoginControllerTest {

    private userLoginController controller;

    @BeforeAll
    public static void setUpDB() throws Exception{
        SQLHelper.startDBConnection("contactTracing.db");
    }

    @BeforeEach
    public void setUp() throws Exception{
        this.controller = new userLoginController();
    }

    @AfterEach
    public void tearDown() throws Exception{
        this.controller = null;
    }

    @Test
    public void testLoginHealthStaff() throws Exception
    {
        controller = new userLoginController();
        HealthStaff result = (HealthStaff)controller.validate("A", "av");
        HealthStaff expected = new HealthStaff(4612, "A", "av", "a@gmail.com");
        assertEquals(expected.getID(), result.getID());
        assertEquals(expected.getEmail(), result.getEmail());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getStaffID(), result.getStaffID());
    }

    @Test
    public void testLoginHealthOrg() throws Exception
    {
        HealthOrganization result = (HealthOrganization)controller.validate("B", "bv");
        HealthOrganization expected = new HealthOrganization(4613, "B", "bv", "b@gmail.com");
        assertEquals(expected.getID(), result.getID());
        assertEquals(expected.getEmail(), result.getEmail());
    }

    @Test
    public void testLoginBusiness() throws Exception
    {
        Business result = (Business)controller.validate("C", "cv");
        Business expected = new Business(4614, "C", "cv", "c@gmail.com");
        assertEquals(expected.getID(), result.getID());
        assertEquals(expected.getEmail(), result.getEmail());
        assertEquals(expected.getAddress(), result.getAddress());
        assertEquals(expected.getBusinessID(), result.getBusinessID());
        assertEquals(expected.getName(), result.getName());
    }

    @Test
    public void testLoginPublicUser() throws Exception
    {
        PublicUser result = (PublicUser)controller.validate("D", "dv");
        PublicUser expected = new PublicUser(4615, "D", "dv", "d@gmail.com");
        assertEquals(expected.getID(), result.getID());
        assertEquals(expected.getEmail(), result.getEmail());
        assertEquals(expected.getIDNum(), result.getIDNum());
        assertEquals(expected.getName(), result.getName());
    }
}

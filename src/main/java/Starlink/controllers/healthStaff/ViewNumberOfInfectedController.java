package Starlink.controllers.healthStaff;

import Starlink.entities.PublicUser;

public class ViewNumberOfInfectedController {
    public int getNumberOfInfected() throws Exception
    {
        PublicUser count = new PublicUser();
        return count.getNumberOfInfected();
    }
}

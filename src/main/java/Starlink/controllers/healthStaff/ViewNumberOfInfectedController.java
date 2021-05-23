package Starlink.controllers.healthStaff;

import Starlink.entities.HealthStaff;
import Starlink.views.CommonUI;

public class ViewNumberOfInfectedController {
    public int getNumberOfInfected() throws Exception
    {
        return ((HealthStaff)CommonUI.getLoggedInUser()).getNumberOfInfected();
    }
}

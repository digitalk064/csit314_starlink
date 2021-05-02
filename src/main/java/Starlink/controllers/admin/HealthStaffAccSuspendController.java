package Starlink.controllers.admin;

import Starlink.entities.HealthStaff;

public class HealthStaffAccSuspendController {
    public boolean suspend(HealthStaff staff) throws Exception{
        staff.setSuspended("yes");
        return true;    
    }
}

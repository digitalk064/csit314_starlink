package Starlink.controllers.admin;

import Starlink.entities.Business;

public class BusinessAccSuspendController {
    public boolean suspend(Business business) throws Exception{
        business.setSuspended("yes");
        return true;    
    }
}

package Starlink.controllers.admin;

import Starlink.entities.PublicUser;

public class PublicAccSuspendController {
    public boolean suspend(PublicUser publicuser) throws Exception{
        publicuser.setSuspended("yes");
        return true;    
    }
}

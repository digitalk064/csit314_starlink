package Starlink.controllers.healthStaff;

import Starlink.entities.PublicUser;

public class InfectionStatusController {
    public boolean setInfectionStatus(PublicUser publicUser, boolean status) throws Exception{
        publicUser.setInfectionStatus(status);
        return true;    
    }
}

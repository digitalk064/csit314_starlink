package Starlink.controllers.publicUser;

import Starlink.entities.PublicUser;

public class viewVaccinationController {
    public boolean getVaxStatus(PublicUser publicUser) throws Exception{
        return publicUser.getVaxStatus();
    }
}

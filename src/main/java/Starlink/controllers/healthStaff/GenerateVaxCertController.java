package Starlink.controllers.healthStaff;

import Starlink.entities.PublicUser;

public class GenerateVaxCertController {
    public boolean setVaccinationStatus(PublicUser publicUser, boolean status) throws Exception{
        publicUser.setVaxStatus(status);
        return true;    
    }
}

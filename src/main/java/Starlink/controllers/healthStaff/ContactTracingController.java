package Starlink.controllers.healthStaff;

import java.util.List;

import Starlink.entities.LocHistory;

public class ContactTracingController {
    public List<LocHistory> getContacts(String IDNum) throws Exception
    {
        LocHistory tracing = new LocHistory();
        List<LocHistory> contacts = tracing.traceContact(IDNum);
        return contacts;
    }
}

package com.pashenko.SpringAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Clinic {
    {
        System.out.println("Clinic created");
    }

    private IPatient patient;

    public void work(){
        this.patient.doSomething();
    }

    @Autowired
//    @Qualifier ("livePatient")            // Not unique bean! definition in config (Uncomment if not)

    public void setPatient(IPatient patient) {
        this.patient = patient;
    }
}

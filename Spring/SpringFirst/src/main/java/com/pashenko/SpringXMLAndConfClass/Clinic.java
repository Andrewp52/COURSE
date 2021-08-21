package com.pashenko.SpringXMLAndConfClass;

public class Clinic {
    {
        System.out.println("Clinic created");
    }

    private IPatient patient;

    public void work(){
        this.patient.doSomething();
    }

    public void setPatient(IPatient patient) {
        this.patient = patient;
    }
}

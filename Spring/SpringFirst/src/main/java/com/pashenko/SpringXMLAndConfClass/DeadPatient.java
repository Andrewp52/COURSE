package com.pashenko.SpringXMLAndConfClass;

public class DeadPatient implements IPatient{

    @Override
    public void doSomething() {
        System.out.println("R.I.P");
    }
}

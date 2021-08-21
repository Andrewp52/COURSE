package com.pashenko.SpringAnnotations;

import org.springframework.stereotype.Component;

@Component
public class DeadPatient implements IPatient {

    @Override
    public void doSomething() {
        System.out.println("R.I.P");
    }
}

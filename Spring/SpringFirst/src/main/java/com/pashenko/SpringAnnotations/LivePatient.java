package com.pashenko.SpringAnnotations;

import org.springframework.stereotype.Component;

@Component
public class LivePatient implements IPatient {

    public void doSomething(){
        System.out.println("Wohoo!");
    }
}

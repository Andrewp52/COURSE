package com.pashenko.SpringAnnotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Config Annotations");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConf.class);
        Clinic clinic = context.getBean("clinic", Clinic.class);
        clinic.work();
    }
}

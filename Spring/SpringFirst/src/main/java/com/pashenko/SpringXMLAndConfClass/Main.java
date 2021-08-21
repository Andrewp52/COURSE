package com.pashenko.SpringXMLAndConfClass;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("/AppConf.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConf.class);
        Clinic clinic = context.getBean("clinic", Clinic.class);
        clinic.work();
    }
}

package com.pashenko.SpringXMLAndConfClass;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConf {

    @Bean
    public IPatient patient(){
        return new DeadPatient();
    }

    @Bean
    public Clinic clinic(){
        Clinic c = new Clinic();
        c.setPatient(patient());
        return c;
    }
}

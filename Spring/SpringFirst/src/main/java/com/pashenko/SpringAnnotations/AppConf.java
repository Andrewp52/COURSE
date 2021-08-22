package com.pashenko.SpringAnnotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConf {

    // Instead of qualifier.
    @Bean
    public IPatient patient(){
        return new LivePatient();
    }
}

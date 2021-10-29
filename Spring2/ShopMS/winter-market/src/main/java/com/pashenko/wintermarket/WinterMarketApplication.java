package com.pashenko.wintermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WinterMarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WinterMarketApplication.class, args);
    }
}

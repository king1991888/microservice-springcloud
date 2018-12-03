package com.king.microservice.provider.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author king
 * 2018/12/1
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderTwoInstanceBApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProviderTwoInstanceBApplication.class,args);
    }
}

package com.king.microservice.discovery.eureka.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author king
 * 2018/12/2
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaCloudInstance1Application {


    public static void main(String[] args) {
        SpringApplication.run(EurekaCloudInstance1Application.class,args);
    }

}

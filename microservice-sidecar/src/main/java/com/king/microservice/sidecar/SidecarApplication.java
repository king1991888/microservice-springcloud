package com.king.microservice.sidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * @author king
 * 2018/12/13
 */
@SpringBootApplication
/**
 * 引入sidecar功能，是一个组合注解，组合了eureka客户端、zuul代理、断路器
 * */
@EnableSidecar
public class SidecarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplication.class,args);
    }

}

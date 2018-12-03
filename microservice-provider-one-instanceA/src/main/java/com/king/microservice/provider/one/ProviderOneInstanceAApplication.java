package com.king.microservice.provider.one;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author king
 * 2018/12/1
 */

@SpringBootApplication
/**@EnableDiscoveryClient   与@EnableEurekaClient注解类似，都是启用注册到服务发现组件上的意思
 * 前者是寻找一切服务发现组件进行注册，可以是zookeeper，eureka等，后者是只寻找eureka组件
 * */
@EnableEurekaClient
public class ProviderOneInstanceAApplication {



    public static void main(String[] args) {
        SpringApplication.run(ProviderOneInstanceAApplication.class,args);
    }

}

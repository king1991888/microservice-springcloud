package com.king.microservice.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author king
 * 2018/12/11
 */
@SpringBootApplication
/**
 * 启用zuul路由网关，是一个组合注解，已组合了@EnableEurekaClient,
 * 所以不需要加eureka客户端启动注解
 * */
@EnableZuulProxy
public class ZuulApplication {


    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }

}

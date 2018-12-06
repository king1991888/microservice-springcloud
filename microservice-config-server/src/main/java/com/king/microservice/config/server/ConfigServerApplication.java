package com.king.microservice.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author huangjie
 * @description
 * @date 2018/12/6
 */
@SpringBootApplication
@EnableConfigServer   //启用配置中心服务端
public class ConfigServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class,args);
    }

}

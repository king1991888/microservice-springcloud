package com.king.microservice.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @author king
 * 2018/12/11
 */
@SpringBootApplication
/**
 * 启用zuul路由网关，是一个组合注解，已组合了@EnableEurekaClient,
 * 所以不需要加eureka客户端启动注解，/routes：此路径可以知道有多少路径被反向代理
 * /routes/details可以知道的更详细，/filters了解过滤器详情
 *@EnableZuulServer:此注解是抛弃zuul的转发特性，保留zuul自己的其他特性，好像没什么用
 * */
@EnableZuulProxy

public class ZuulApplication {


    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }

}

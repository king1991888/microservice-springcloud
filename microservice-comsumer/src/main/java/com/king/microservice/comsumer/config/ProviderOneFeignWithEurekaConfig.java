package com.king.microservice.comsumer.config;

import com.king.microservice.comsumer.annotation.ExcludeFeignConfig;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author king
 * 2018/12/2
 */

/**
 * 让springboot父容器排除扫描该配置，避免该配置全局化
 * */
@ExcludeFeignConfig
/**
 * 脱离eureka进行rest请求的feign客户端配置
 * */
@SpringBootConfiguration

public class ProviderOneFeignWithEurekaConfig {

    /**
     * 请求身份认证配置用户名密码
     * */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("root", "root");
    }

    /**
     * 禁用单个feign的hystrix
     * */
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }


}

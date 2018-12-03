package com.king.microservice.comsumer.config;

import com.king.microservice.comsumer.annotation.ExcludeFeignConfig;
import feign.Contract;
import feign.Logger;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author king
 * 2018/12/2
 */

/**
 * 让springboot父容器排除扫描该配置，避免该配置全局化
 * */
@ExcludeFeignConfig
/**
 * 自定义feign的配置
 * */
@SpringBootConfiguration

public class ProviderOneFeignConfig {


    /**
     * 配置feign的契约为默认的契约配置
     * */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    /**
     * 配置feign的logger日志打印级别
     * */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


}

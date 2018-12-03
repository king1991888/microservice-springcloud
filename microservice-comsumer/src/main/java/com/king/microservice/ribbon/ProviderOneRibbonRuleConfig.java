package com.king.microservice.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author king
 * 2018/12/1
 */
@SpringBootConfiguration
public class ProviderOneRibbonRuleConfig {

    /**
     * 配置负载均衡算法为随机规则
     * */
    @Bean
    public IRule providerOneRule(){
        return new RandomRule();
    }

}

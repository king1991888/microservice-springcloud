package com.king.microservice.comsumer;

import com.king.microservice.comsumer.annotation.ExcludeFeignConfig;
import com.king.microservice.ribbon.ProviderOneRibbonRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * @author king
 * 2018/12/1
 */
/**
 * springboot父容器排除feign的自定义配置类
 * */
@ComponentScan(excludeFilters ={ @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFeignConfig.class)})
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients  //使用feign客户端，ribbon的负载均衡规则仍然有效
@EnableCircuitBreaker   //启用hystrix断路器
@EnableHystrixDashboard  //启用断路器视图监控，路径：/hystrix,监控数据：/hystrix.stream,需要在健康检查那里加上
/**
 * 配置ribbon负载均衡算法，name是指定microservice-provider-one为服务名的算法为一个
 * 配置类，在该配置类里面配置具体的算法的bean，注意，该配置类不可以放在springboot父容器，
 * 也就是说不能放在@SpringBootApplication默认扫描的包和子包里面，也不能放在@ComponentScan
 * 注解能够扫描到的包里面，否则该配置类配置的负载均衡算法规则将视为全局规则，对全部服务的调取
 * 都生效。
 * @RibbonClient(name="microservice-provider-one",configuration = ProviderOneRibbonRuleConfig.class)
 * 如果@RibbonClient注解的配置类一定要放在父容器当中，那么在配置ComponentScan注解的时候，
 * 需加上以下配置，然后再自定义一个@ExcludeFromComponentScan的注解，再在该配置类中加上
 * 自己定义的注解即可。意思是说，ComponentScan在扫描spring的注解生成Bean放入springboot父容器
 * 的时候，将排除含有ExcludeFromComponentScan注解的类，如此，负载均衡规则类就不会被全局扫描。
* @ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
* */
public class ComsumerApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



    public static void main(String[] args) {
        SpringApplication.run(ComsumerApplication.class,args);
    }


}

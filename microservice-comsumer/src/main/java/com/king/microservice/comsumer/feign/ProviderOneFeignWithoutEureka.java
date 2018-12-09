package com.king.microservice.comsumer.feign;

import com.king.microservice.comsumer.config.ProviderOneFeignWithEurekaConfig;
import com.king.microservice.comsumer.feign.impl.ProviderOneFeignWithoutEurekaImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author king
 * 2018/12/2
 */
/**
 * 脱离eureka进行rest请求的feign客户端，name不再指在eureka中的服务的名字，只是指定feign客户端的
 * 名字而已。url是请求的地址，configuration是配置类，现在进行eureka的请求
 * */
@FeignClient(name="handleEureka",url="http://localhost:8765",configuration = ProviderOneFeignWithEurekaConfig.class
,fallback = ProviderOneFeignWithoutEurekaImpl.class)
public interface ProviderOneFeignWithoutEureka {

    /**进行地址为http://localhost:8765/eureka/apps/{serviceName}的请求，完整路径由@FeignClient
     * 注解的url属性+以下@RequestMapping注解的value属性拼接，遵循springmvc风格
     * */
    @RequestMapping(value = "/eureka/apps/{serviceName}")
    public String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName);



}

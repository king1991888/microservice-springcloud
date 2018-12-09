package com.king.microservice.comsumer.feign;

import com.king.microservice.comsumer.config.ProviderOneFeignConfig;
import com.king.microservice.entity.BaseResult;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * @author king
 * 2018/12/2
 */
/**
 * feign客户端的另外一种形式,覆盖掉feign的原生只支持springmvc的契约等配置方式，也就是说原生的feign
 * 客户端配置方式将会失效，如果项目中还存在原生feign客户端的接口，将会被认为不符合契约而报错
 * 自定义feign配置的feign客户端，从eureka调取的服务是microservice-provider-one,配置类是ProviderOneFeignConfig
 * 注意，该配置类不能被父容器扫描到
 * */
/*@FeignClient(name="microservice-provider-one",configuration = ProviderOneFeignConfig.class)
public interface SelfConfigProviderOneFeign {

    *//**
     * feign的默认契约请求格式，使用@RequestLine注解，请求方式(大写)+空格+url
     * *//*
    @RequestLine("GET /handler")
    public BaseResult test();



}*/

package com.king.microservice.comsumer.feign;

import com.king.microservice.entity.BaseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author king
 * 2018/12/1
 */
/**
 * 原生feign客户端调用microservice-provider-one服务，只支持springmvc方式，会与自定义配置的feign
 * 客户端冲突
 */

@FeignClient("microservice-provider-one")
public interface ProviderOneFeign {

    //调取handler接口，两个坑：1. @GetMapping不支持   2. @PathVariable得设置value
    @RequestMapping(value = "/handler",method = RequestMethod.GET)
    public BaseResult test();


    /**
     * 调取objHandle接口,对于请求参数是对象的，要设置consumes为json格式，并且要替换
     * feign的原生调用http接口的方式，feign原生调接口方式是使用jdk的httpurlconnection，
     * 需要替换成apache的httpclient框架
     */
    @RequestMapping(value="/objHandle",method=RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResult objHandle(@RequestBody BaseResult baseResult);

}

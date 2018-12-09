package com.king.microservice.comsumer.feign;

import com.king.microservice.comsumer.feign.impl.ProviderOneFeignFallbackFactoryImpl;
import com.king.microservice.entity.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author king
 * 2018/12/9
 */
/**
 * 使用fallbackFactory属性能追踪到调用服务失败的原因
 * */
@FeignClient(name="microservice-provider-one",fallbackFactory = ProviderOneFeignFallbackFactoryImpl.class)
public interface ProviderOneFeignWithFallbackFactory {


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

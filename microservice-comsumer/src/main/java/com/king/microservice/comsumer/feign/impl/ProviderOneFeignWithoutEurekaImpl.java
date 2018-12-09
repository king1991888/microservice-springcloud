package com.king.microservice.comsumer.feign.impl;

import com.king.microservice.comsumer.feign.ProviderOneFeignWithoutEureka;
import org.springframework.stereotype.Component;

/**
 * @author king
 * 2018/12/9
 */@Component
public class ProviderOneFeignWithoutEurekaImpl implements ProviderOneFeignWithoutEureka {

    @Override
    public String findServiceInfoFromEurekaByServiceName(String serviceName) {
        return "调用"+this.getClass().getSimpleName()+"的fallback方法";
    }
}

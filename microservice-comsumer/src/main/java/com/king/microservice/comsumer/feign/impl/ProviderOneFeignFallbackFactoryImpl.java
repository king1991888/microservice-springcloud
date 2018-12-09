package com.king.microservice.comsumer.feign.impl;

import com.king.microservice.comsumer.feign.ProviderOneFeignWithFallbackFactory;
import com.king.microservice.entity.BaseResult;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author king
 * 2018/12/9
 */
/**
 * 进行回退的时候打印出异常日志
 * */
@Component
public class ProviderOneFeignFallbackFactoryImpl implements FallbackFactory<ProviderOneFeignWithFallbackFactory> {


    private static final Logger LOGGER= LoggerFactory.getLogger(ProviderOneFeignFallbackFactoryImpl.class);

    @Override
    public ProviderOneFeignWithFallbackFactory create(Throwable throwable) {
        LOGGER.error(throwable+"");


        return new ProviderOneFeignWithFallbackFactory(){

            @Override
            public BaseResult test() {
                return new BaseResult("调用fallbackFactory的方法");
            }

            @Override
            public BaseResult objHandle(BaseResult baseResult) {
                return  new BaseResult("调用fallbackFactory的方法");
            }
        };
    }
}

package com.king.microservice.comsumer.feign.impl;

import com.king.microservice.comsumer.feign.ProviderOneFeign;
import com.king.microservice.entity.BaseResult;
import org.springframework.stereotype.Component;

/**
 * @author king
 * 2018/12/9
 */

/**
 * feign的实现类，使用feign调用服务的时候发生超时，将启用断路器，调用实现类对应的实现方法
 * 注意要将此类注入到spring容器
 * */
@Component
public class ProviderOneFeignImpl implements ProviderOneFeign {


    @Override
    public BaseResult test() {
        return new BaseResult("调用feign的fallback方法");
    }

    @Override
    public BaseResult objHandle(BaseResult baseResult) {
        return  new BaseResult("调用feign的fallback方法");
    }
}

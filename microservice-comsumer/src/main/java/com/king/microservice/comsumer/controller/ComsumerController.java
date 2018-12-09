package com.king.microservice.comsumer.controller;

import com.king.microservice.comsumer.feign.ProviderOneFeign;
import com.king.microservice.comsumer.feign.ProviderOneFeignWithFallbackFactory;
import com.king.microservice.comsumer.feign.ProviderOneFeignWithoutEureka;
import com.king.microservice.entity.BaseResult;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author king
 * 2018/12/1
 */


@RestController
public class ComsumerController {


    @Autowired
    private RestTemplate restTemplate;//使用基于rest风格的httpclient调取服务接口的对象

    @Autowired
    private EurekaClient eurekaClient;//eureka客户端，可以获取服务实例信息

    @Autowired
    private LoadBalancerClient loadBalancerClient;//ribbon负载均衡器对象

    @Autowired
    private ProviderOneFeign providerOneFeign;

    @Autowired
    private ProviderOneFeignWithFallbackFactory providerOneFeignWithFallbackFactory;

 /*   @Autowired
    private SelfConfigProviderOneFeign selfConfigProviderOneFeign;*/

    @Autowired
    private ProviderOneFeignWithoutEureka providerOneFeignWithoutEureka;


    @GetMapping("/handle/providerone")
    public String handleService(){

        //调取服务实例名为microservice-provider-one的/handler接口
        String result=restTemplate.getForObject("http://microservice-provider-one/handler",String.class);

        return result;

    }

    @GetMapping("/getproperties")
    public InstanceInfo getProperties(){
        InstanceInfo providerOne=eurekaClient.getNextServerFromEureka("microservice-provider-one",false);
        return providerOne;
    }

    @GetMapping("/testLoadBalance")
    public ServiceInstance[] testLoadBalance(){
        //通过ribbon负载均衡器获取服务实例信息，默认是轮询算法
        ServiceInstance serviceInstance=loadBalancerClient.choose("microservice-provider-one");
        System.out.println("调取服务1的端口号"+serviceInstance.getPort());

        ServiceInstance serviceInstance2=loadBalancerClient.choose("microservice-provider-two");
        System.out.println("调取服务2的端口号"+serviceInstance2.getPort());
        return new ServiceInstance[]{serviceInstance,serviceInstance2};

    }

    @GetMapping("/testFeign")
    /**
     * 使用断路器保护接口，如果接口方法里面出现超时，断路器将开启，不执行接口里面的代码，
     * 直接调用fallback里面指定的方法，注意fallbackmethod属性指定的方法的方法名和返回值访问修饰符等
     * 要和接口的方法一致。
     * */
    @HystrixCommand(fallbackMethod = "testFeignFallback")
    public BaseResult testFeign() {
        return providerOneFeign.objHandle(new BaseResult("testfeign"));
    }

    public BaseResult testFeignFallback(){
        return new BaseResult("调用fallback方法");
    }

    @GetMapping("/test")
    public BaseResult test()  {

        return providerOneFeign.test();
    }

    @GetMapping("/testCutomizingFeign/{serviceName}")
    public String testCutomizingFeign(@PathVariable String serviceName){

       /* System.out.println(selfConfigProviderOneFeign.test().getMsg());*/
        return providerOneFeignWithoutEureka.findServiceInfoFromEurekaByServiceName(serviceName);

    }

    @GetMapping("/testFallbackFactory")
    public BaseResult testFallbackFactory(){
        return providerOneFeignWithFallbackFactory.test();
    }


}

package com.king.microservice.provider.two.controller;


import com.king.microservice.entity.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author king
 * 2018/12/1
 */
@RestController
public class ProviderTwoController {


    @GetMapping("/handler")
    public BaseResult test(){
        String result="服务2实例B被调用";
        System.out.println(result);
        return new BaseResult(result);
    }

}

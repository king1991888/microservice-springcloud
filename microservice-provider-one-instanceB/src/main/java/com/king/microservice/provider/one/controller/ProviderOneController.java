package com.king.microservice.provider.one.controller;


import com.king.microservice.entity.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author king
 * 2018/12/1
 */
@RestController
public class ProviderOneController {


    @GetMapping("/handler")
    public BaseResult test(){
        String result="服务1实例B被调用";
        System.out.println(result);
        return new BaseResult(result);
    }

    @GetMapping("/objHandle")
    public BaseResult postHandler(@RequestBody BaseResult baseResult){
        System.out.println("服务1实例B的传入对象的方法被调用");
        return baseResult;
    }

}

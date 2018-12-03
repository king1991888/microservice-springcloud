package com.king.microservice.entity;

/**
 * @author king
 * 2018/12/1
 */
public class BaseResult {

    private String msg;

    public BaseResult(){

    }

    public BaseResult(String msg){
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

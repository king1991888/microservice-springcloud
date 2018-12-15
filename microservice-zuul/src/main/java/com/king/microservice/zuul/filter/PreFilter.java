package com.king.microservice.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author king
 * 2018/12/15
 */
/**
 * zuul的过滤器，继承com.netflix.zuul.ZuulFilter类,重新一系列方法。注入spring容器。
 *
 * */
@Component
public class PreFilter extends ZuulFilter {
    @Override
    /**
     * 指定过滤器类型，有三种
     * pre：客户端请求到zuul服务之前的过滤器
     * route：zuul转发请求到下游服务的过滤器
     * post：请求转发返回的时候触发的过滤器
     * */
    public String filterType() {
        return "pre";
    }

    @Override
    /**
     * 过滤器顺序，从小到大
     * */
    public int filterOrder() {
        return 0;
    }

    @Override
    /**
     * 是否启用该过滤器
     * */
    public boolean shouldFilter() {
        return true;
    }

    @Override
    /**
     * 过滤器执行逻辑
     * */
    public Object run() throws ZuulException {
        RequestContext ctx=RequestContext.getCurrentContext();
        //获取request请求对象
        HttpServletRequest request=ctx.getRequest();
        String url=request.getRequestURL().toString();
        if(url.equalsIgnoreCase("anon")){
            //让请求继续
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(HttpStatus.SC_OK);
            ctx.set("requestSuccess",true);
        }else{
            //让路由请求终止，直接返回错误码和错误内容
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            ctx.setResponseBody("request failed");
            ctx.set("requestSuccess",false);
        }


        return null;
    }
}

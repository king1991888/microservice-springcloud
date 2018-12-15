package com.king.microservice.zuul.config;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author king
 * 2018/12/13
 */
/**
 * 为反向代理转发下游服务提供zuul整合的断路器hystrix的fallback返回
 * */
@Component
public class ProviderOneFallback implements FallbackProvider {

    @Override
    public String getRoute() {
        //为服务microservice-provider-one提供fallback
        return "microservice-provider-one";
    }

    @Override
    //请求服务失败时捕捉的异常，可以在此打印调用失败的原因作为日志，返回后调用response方法
    public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
        //如果是超时异常则返回504，反则返回500，可自定义
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //最终返回的方法
    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            //返回体
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("fallback".getBytes());
            }

            @Override
            //返回头
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}

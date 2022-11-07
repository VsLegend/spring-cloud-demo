package com.example.client1.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

/**
 * @author wong
 * @date 2021/6/16 14:57
 * @description
 */

@Configuration
public class FeignErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {
        System.out.println(methodKey);
        System.out.println(response.reason());
        return null;
    }

}

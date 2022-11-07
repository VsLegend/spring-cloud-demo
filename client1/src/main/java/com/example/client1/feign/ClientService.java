package com.example.client1.feign;

import com.example.client1.config.FeignConfig;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @User: Administrator
 * @Time: 2021/5/21
 * @Description:
 */

@FeignClient(name = "eureka-client-01", configuration = FeignConfig.class)
public interface ClientService {

    @RequestLine("GET /client/getInfo")
    public String getInfo();

}

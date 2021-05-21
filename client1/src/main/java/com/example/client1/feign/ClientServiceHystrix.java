package com.example.client1.feign;

import org.springframework.stereotype.Component;

/**
 * @User: Administrator
 * @Time: 2021/5/21
 * @Description:
 */

@Component
public class ClientServiceHystrix implements ClientService {
    @Override
    public String getInfo() {
        return null;
    }
}

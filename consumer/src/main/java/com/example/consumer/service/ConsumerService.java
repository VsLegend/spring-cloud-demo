package com.example.consumer.service;

import com.example.consumer.service.hystrix.ConsumerHystrix;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Wangjunwei
 * @Date 2019/9/18 16:46
 * @Description
 */

/**
 * 注解@FeignClient用以调用对应服务名的接口。
 * fallback: Hystrix支持回退(fallback)的概念：当调用代码出现错误时，不会直接中断程序，而是调用其回退类的对应方法
 * |@FeignClient添加fallback属性，并在fallback中设置实现回退的类名，即可实现回退
 * Feign包含了ribbon支持，@FeignClient已经使用Ribbon的负载均衡功能，其调用服务会以轮询的方式进行
 */
@Component
@FeignClient(value = "eureka-client-01", fallback = ConsumerHystrix.class)
public interface ConsumerService {

  @GetMapping(value = "/client/getInfo")
  @LoadBalanced //注解描述的是一个负载均衡, 由于feign已经有了负载功能，因此这里可以省略
  String getclientInfo();

  @GetMapping(value = "/clientSecond/method")
  String getClientSecondInfo();

  @GetMapping(value = "/clientSecond/getWhenError")
  String getClientSecondError();
}

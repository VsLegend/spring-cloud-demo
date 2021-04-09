package com.example.gateway.controller;

import com.example.gateway.service.ConsumerService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjunwei
 * @Date 2019/9/18 16:42
 * @Description
 */

@RestController
@RequestMapping("/zuulTest")
public class ConsumerController {

  @Resource
  ConsumerService consumerService;

  @GetMapping("/getclientInfo")
  public String getclientInfo() {
    return consumerService.getclientInfo();
  }

  @GetMapping("/getClientSecondInfo")
  public String getClientSecondInfo() {
    return consumerService.getClientSecondInfo();
  }

  @GetMapping("/getClientSecondError")
  public String getClientSecondError() {
    return consumerService.getClientSecondError();
  }
}

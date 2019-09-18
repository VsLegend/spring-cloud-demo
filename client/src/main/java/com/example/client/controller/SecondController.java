package com.example.client.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjunwei
 * @Date 2019/8/7 10:56
 * @Description
 */
@RestController
@RequestMapping("/clientSecond")
public class SecondController {

  @GetMapping("/method")
  public String secondMethod() {
    return "服务777: 这是服务777为您服务";
  }

  @GetMapping("/getWhenError")
  public String getName() {
    double i = 99/0;
    return "服务777: ---------------------你好，Bob-----------------------";
  }
}

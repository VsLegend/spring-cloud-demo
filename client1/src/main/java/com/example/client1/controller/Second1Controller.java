package com.example.client1.controller;

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
public class Second1Controller {

  @GetMapping("/method")
  public String secondMethod() {
    return "服务4396: 4396为您服务";
  }

  @GetMapping("/getWhenError")
  public String getName() {
    return "服务4396: ---------------------你好，Bob-----------------------";
  }
}

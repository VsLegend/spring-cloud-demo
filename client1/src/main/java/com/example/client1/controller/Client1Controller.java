package com.example.client1.controller;

import com.example.client1.service.DataService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjunwei
 * @Date 2019/8/6 17:25
 * @Description
 */
@RestController
@RequestMapping("/client")
public class Client1Controller {

  @Resource
  DataService dataService;

  @GetMapping("/getInfo")
  public String getInfo() {
    return "client1 返回数据";
  }
}

package com.example.client.controller;

import com.example.client.service.DataService;
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
public class ClientController {

  @Resource
  DataService dataService;

  @GetMapping("/getInfo")
  public String getInfo() {
    return "client 返回数据";
  }
}

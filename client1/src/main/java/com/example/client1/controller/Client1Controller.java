package com.example.client1.controller;

import com.example.client1.feign.ClientService;
import com.example.client1.service.DataService;
import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjunwei
 * @Date 2019/8/6 17:25
 * @Description
 */
@Api(tags = "client2")
@RestController
@RequestMapping("/client2")
public class Client1Controller {

  @Resource
  ClientService clientService;

  @ApiOperation(value = "请求接口")
  @GetMapping("/getInfo")
  public String getInfo() {
    return "client1 返回数据";
  }

  @ApiOperation(value = "远程feign请求")
  @GetMapping("/getClient1Info")
  public String getClient1Info() {
    return clientService.getInfo();
  }
}

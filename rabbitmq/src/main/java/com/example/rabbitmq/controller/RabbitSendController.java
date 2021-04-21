package com.example.rabbitmq.controller;

import com.example.rabbitmq.constant.RabbitMqConstants;
import com.example.rabbitmq.mq.RabbitSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @User: wong
 * @Date: 2021/4/21
 * @Description: 测试rabbitmq消息发送
 */

@RestController
@RequestMapping("/rabbitMQ")
public class RabbitSendController {

  @Resource
  private RabbitSender rabbitSender;

  @GetMapping("/sendMsg/{msg}")
  public void sendMsg(@PathVariable("msg") String msg) {
    rabbitSender.send(msg, RabbitMqConstants.LOG_MESSAGE_ROUTE);
  }

  @GetMapping("/sendMsgWithTTL/{msg}/{ttl}")
  public void sendMsgWithTTL(@PathVariable("msg") String msg, @PathVariable("ttl") String ttl) {
    rabbitSender.send(msg, RabbitMqConstants.LOG_MESSAGE_ROUTE, ttl);
  }

}

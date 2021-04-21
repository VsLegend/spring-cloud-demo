package com.example.rabbitmq.mq;

import com.example.rabbitmq.constant.RabbitMqConstants;
import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.UUID;

/**
 * @User: wong
 * @Date: 2021/4/21
 * @Description: rabbitmq消息发送工具类
 */
@Component
@Slf4j
public class RabbitSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {


  @Resource
  private RabbitTemplate rabbitTemplate;

  @PostConstruct
  public void init() {
    rabbitTemplate.setConfirmCallback(this);
    rabbitTemplate.setReturnsCallback(this);
  }

  /**
   * 发送消息确认
   *
   * @param ack   ack 回执 表示消息发送成功
   * @param cause cause 失败原因
   */
  @Override
  public void confirm(CorrelationData correlationData, boolean ack, String cause) {
    if (ack) {
      System.out.println("消息发送成功:" + correlationData);
    } else {
      System.out.println("消息发送失败:" + cause);
    }
  }

  @Override
  public void returnedMessage(ReturnedMessage returned) {
    String exchange = returned.getExchange();
    String routingKey = returned.getRoutingKey();
    int replyCode = returned.getReplyCode();
    String replyText = returned.getReplyText();
    log.info("发送的消息返回体：{}", returned.toString());
    Message message = returned.getMessage();
    message.getBody();
    MessageProperties properties = message.getMessageProperties();
    log.info("消息体信息：{}", properties.toString());
    log.error(properties.getCorrelationId() + " 发送失败");
  }

  /**
   * 发送消息
   */
  public void send(String msg, String routeKey) {
    CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
    log.info("开始发送消息 : " + routeKey);
    rabbitTemplate.convertAndSend(RabbitMqConstants.TOPIC_EXCHANGE_NORMAL_LOG, routeKey, msg, correlationId);
    log.info("结束发送消息 : " + routeKey);
    log.info("消息发送成功");
  }

  /**
   * 发送消息
   */
  public void send(String msg, String routeKey, String ttl) {
    CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
    MessageProperties messageProperties = new MessageProperties();
    messageProperties.setExpiration(ttl); // 过期时间
    Message message = new Message(msg.getBytes(), messageProperties);

    log.info("开始发送消息 : " + routeKey);
    rabbitTemplate.send(RabbitMqConstants.TOPIC_EXCHANGE_NORMAL_LOG, routeKey, message, correlationId);
    log.info("结束发送消息 : " + routeKey);
    log.info("消息发送成功");
  }

}

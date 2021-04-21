package com.example.rabbitmq.mq;

import com.example.rabbitmq.constant.RabbitMqConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @User: wong
 * @Date: 2021/4/21
 * @Description:
 */
@Component
@Slf4j
public class RabbitReceiver {


  /**
   * 监听消息返回
   */
  @RabbitListener(queues = RabbitMqConstants.DEAD_LETTER_QUEUE)
  @RabbitHandler
  public void biddingDeadQueue1(Message message, Channel channel) throws Exception {
    log.info("接收到{}， 的消息", RabbitMqConstants.DEAD_LETTER_QUEUE);
    String json = new String(message.getBody(), StandardCharsets.UTF_8);
    log.info("消息内容：{}", json);
    channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
  }

  /**
   * 监听消息返回
   */
  @RabbitListener(queues = RabbitMqConstants.DEAD_LETTER_QUEUE)
  @RabbitHandler
  public void biddingDeadQueue2(Message message, Channel channel) throws Exception {
    log.info("接收到{}， 的消息", RabbitMqConstants.DEAD_LETTER_QUEUE);
    String json = new String(message.getBody(), StandardCharsets.UTF_8);
    log.info("消息内容：{}", json);
    channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
  }
}
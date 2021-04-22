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
    /**
     * 手动返回ack确认消息
     * DeliveryTag：它代表了 RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，是一个单调递增的正整数，delivery tag 的范围仅限于 Channel
     * multiple：为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
     */
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
    /**
     * 手动nack
     * requeue：表明是否需要再次入队重试
     */
    channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, false);
  }
}
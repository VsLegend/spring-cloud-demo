package com.example.rabbitmq.config;

import com.example.rabbitmq.constant.RabbitMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @User: wong
 * @Date: 2021/4/21
 * 配置日志发送的mq
 */

@Configuration
public class RabbitConfig {

  // topic交换机： 工作队列的交换机、绑定设置

  /**
   * 工作交换机
   */
  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(RabbitMqConstants.TOPIC_EXCHANGE_NORMAL_LOG);
  }

  /**
   * 将工作队列绑定至指定topic交换机
   */
  @Bean
  Binding binding() {
    return BindingBuilder.bind(workQueue()).to(topicExchange()).with(RabbitMqConstants.LOG_MESSAGE_ROUTE);
  }

  /**
   * 工作队列
   */
  @Bean
  public Queue workQueue() {
    return QueueBuilder.durable(RabbitMqConstants.LOG_MESSAGE_QUEUE)
            .withArgument("x-dead-letter-exchange", RabbitMqConstants.TOPIC_EXCHANGE_DEAD_LETTER_LOG)//设置死信交换机
            .withArgument("x-message-ttl", 1000 * 60) // 60s ttl消息存活时间 毫秒ms
            // 用来描述期限，与ttl不同，它必须为正整数（ttl可以为0，表示消息不能被立即删除就直接进入死信队列）
//            .withArgument("x-expires", 1000 * 60) // 60s ttl消息存活时间 毫秒ms
            .withArgument("x-dead-letter-routing-key", RabbitMqConstants.DEAD_LETTER_QUEUE_ROUTE)//设置死信routingKey
            .build();
  }

  // 死信队列的交换机、消息队列设置

  @Bean
  public TopicExchange deadLetterExchange() {
    return new TopicExchange(RabbitMqConstants.TOPIC_EXCHANGE_DEAD_LETTER_LOG);
  }

  @Bean
  public Queue deadLetterQueue() {
    return QueueBuilder.durable(RabbitMqConstants.DEAD_LETTER_QUEUE)
            .build();
  }

  @Bean
  public Binding deadLetterBinding() {
    return BindingBuilder.bind(deadLetterQueue())
            .to(deadLetterExchange())
            .with(RabbitMqConstants.DEAD_LETTER_QUEUE_ROUTE);
  }


  //

  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange("fanout.Exchange");
  }

  /**
   * 将工作队列绑定至指定topic交换机
   */
//  @Bean
//  Binding bindingFanout() {
//    return BindingBuilder.bind(workQueue()).to(fanoutExchange());
//  }
}

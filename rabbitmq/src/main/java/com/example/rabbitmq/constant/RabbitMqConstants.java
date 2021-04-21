package com.example.rabbitmq.constant;

public class RabbitMqConstants {

  /**
   * 业务交换机
   */

  public static final String TOPIC_EXCHANGE_NORMAL_LOG = "topic.log";

  /**
   * 死信队列 交换机
   */
  public static final String TOPIC_EXCHANGE_DEAD_LETTER_LOG = "topic.dead.letter.log";

  /**
   * queue 工作队列
   * route 路由 用于指定topic中的某一个确定路由
   */

  public static final String LOG_MESSAGE_QUEUE = "log.message.queue";


  public static final String LOG_MESSAGE_ROUTE = "log.message.routeKey";


  /**
   * 死信队列 与路由
   */
  public static final String DEAD_LETTER_QUEUE = "dl.queue";


  public static final String DEAD_LETTER_QUEUE_ROUTE = "dl.queue.routeKey";
}

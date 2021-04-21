package com.example.producer.rocketMQ;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @User: wong
 * @Date: 2021/4/16
 * @Description: mq消息接受
 */
public class ConsumerMQReceive {

  // 拉取消费
  private DefaultMQPushConsumer consumer;

  // name server 地址
  private final String nameServer = "127.0.0.1:9876";

  // 订阅的主题
  private final String topics = "test";

  // 订阅的标签 *表示所有
  private final String tags = "*";

  ConsumerMQReceive(String groupName) {
    init(groupName);
  }

  private void init(String groupName) {
    if (null == consumer) {
      this.consumer = new DefaultMQPushConsumer(groupName);
    }
  }

  // 注册信息
  public void setConfig() throws MQClientException {
    // 设置NameServer名字服务 的地址 用以获取broker的路由
    consumer.setNamesrvAddr(nameServer);
    // 可以订阅和消费多个主题和标签
    consumer.subscribe(topics, tags);
  }

  // 启动
  public void startConsumer() throws MQClientException {
    // 注册回调实现类来处理从broker拉取回来的消息
    consumer.registerMessageListener(new MessageListenerConcurrently() {
      @Override
      public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        // 处理消息
        msgs.forEach(messageExt -> {
          System.out.println("消息id:" + messageExt.getMsgId() + " 内容：" + new String(messageExt.getBody()));
          System.out.println(messageExt.toString());
        });
        MessageQueue messageQueue = context.getMessageQueue();  
        System.out.println(context.getAckIndex() + context.getDelayLevelWhenNextConsume());
        // ack回调，向服务器说明该消息已被消费
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
      }
    });
    // 启动mq实例
    consumer.start();
  }

  // 结束
  public void shutDownConsumer() {
    consumer.shutdown();
  }

  public static void main(String[] args) throws MQClientException {
    ConsumerMQReceive mqReceive = new ConsumerMQReceive("consumer-type-1");
    mqReceive.setConfig();
    mqReceive.startConsumer();
  }


}

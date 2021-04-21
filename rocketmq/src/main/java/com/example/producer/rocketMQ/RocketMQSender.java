package com.example.producer.rocketMQ;

import com.example.producer.utils.BeanCopy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @User: wong
 * @Date: 2021/4/15
 * @Description: mq消息发送
 */
@Slf4j
public class RocketMQSender {

  private DefaultMQProducer producer;

  // name server 地址
  private String nameServer = "127.0.0.1:9876";

  // 订阅的主题
  public final String topics = "test";

  // 订阅的标签 *表示所有
  public final String tags = "test1";

  RocketMQSender(String groupName) {
    init(groupName);
  }

  private void init(String groupName) {
    if (null == producer) {
      this.producer = new DefaultMQProducer(groupName);
    }
  }

  // 启动
  public void startProducer() throws MQClientException {
    // 设置NameServer名字服务 的地址 用以获取broker的路由
    producer.setNamesrvAddr(nameServer);
    // 启动mq实例
    producer.start();
  }

  // 结束
  public void shutDownProducer() {
    producer.shutdown();
  }

  // 同步发送消息
  public SendResult sentMassage(Message message) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
    return producer.send(message);
  }

  // 异步发送消息
  public void sentMassageNonSycn(Message message) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
    producer.send(message, new SendCallback() {
      @Override
      public void onSuccess(SendResult sendResult) {
        // 异步处理
        System.out.println(sendResult.getMsgId() + ": " + sendResult.toString());
      }

      @SneakyThrows
      @Override
      public void onException(Throwable e) {
        throw new Exception(e);
      }
    });
  }


  public static void main(String[] args) throws UnsupportedEncodingException, MQClientException {
    RocketMQSender mqSender = new RocketMQSender("produce-type-1"); // 为生产消息的生产者分组
    mqSender.startProducer();
    Message message = new Message();
    message.setKeys(UUID.randomUUID().toString()); // 设置任务的ID 尽量保证唯一 可以定位消息的丢失
    message.setTopic(mqSender.topics); // 主题 表示一类消息的集合
    message.setTags(mqSender.tags); // 主题下的标签，用以区分主题内的不同消息类型
    message.setBody("Transfer message test".getBytes(RemotingHelper.DEFAULT_CHARSET)); // 消息体，即要发送的消息内容，以二进制方式发送
    try {
      SendResult sendResult = mqSender.sentMassage(message);
      /**
       * msgId一定是全局唯一标识符，但是实际使用中，可能会存在相同的消息有两个不同msgId的情况（消费者主动重发、因客户端重投机制导致的重复等）
       * 这种情况就需要使业务字段进行重复消费。
       */
      System.out.println(sendResult.getMsgId() + ": " + sendResult.toString());
    } catch (InterruptedException | RemotingException | MQBrokerException e) {
      e.printStackTrace();
    }
    mqSender.shutDownProducer();
  }

}

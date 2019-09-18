package com.example.consumer.service.hystrix;

import com.example.consumer.service.ConsumerService;
import org.springframework.stereotype.Service;

/**
 * @author Wangjunwei
 * @Date 2019/9/18 16:50
 * @Description
 */
@Service
public class ConsumerHystrix implements ConsumerService {

  @Override
  public String getclientInfo() {
    return "getclientInfo";
  }

  @Override
  public String getClientSecondInfo() {
    return "getClientSecondInfo";
  }

  @Override
  public String getClientSecondError() {
    return "调用方法出现错误，将人工排查数据。请悉知。";
  }
}

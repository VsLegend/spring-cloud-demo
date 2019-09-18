package com.example.client.service.impl;

import com.example.client.service.DataService;
import org.springframework.stereotype.Service;

/**
 * @author Wangjunwei
 * @Date 2019/8/6 16:30
 * @Description
 */
@Service
public class DataServiceImpl implements DataService {

  @Override
  public String getData() {
    return "==============How`s going, bro===============";
  }
}

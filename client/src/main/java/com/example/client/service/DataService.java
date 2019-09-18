package com.example.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Wangjunwei
 * @Date 2019/8/6 16:08
 * @Description
 */

public interface DataService {

  String getData();
}

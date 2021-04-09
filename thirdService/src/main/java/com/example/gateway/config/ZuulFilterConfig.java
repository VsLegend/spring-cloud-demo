package com.example.gateway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

/**
 * @author Wangjunwei
 * @Date 2019/10/10 17:32
 * @Description
 */

@Component
public class ZuulFilterConfig extends ZuulFilter {

  /**
   * 路由的类型 pre：路由之前 routing：路由之时 post： 路由之后 error：发送错误调用
   */
  @Override
  public String filterType() {
    return "pre";
  }

  /**
   * 过滤顺序
   */
  @Override
  public int filterOrder() {
    return 0;
  }

  /**
   * 是否应该过滤，可添加过滤规则，返回true时过滤 false不过滤
   */
  @Override
  public boolean shouldFilter() {
//    if (something) {
//      return true;
//    }
    return false;
  }

  /**
   * 具体逻辑 可以再次进行权限判断等操作进行过滤
   */
  @Override
  public Object run() throws ZuulException {
    return null;
  }


  public static void main(String[] args) {
  }
}

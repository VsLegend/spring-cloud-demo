//package com.example.gateway.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Objects;
//
///**
// * @User: wong
// * @Date: 2020/11/26
// * @Description: 网关拦截 统一加解密
// */
//
//@Slf4j
//@WebFilter(urlPatterns = "/*")
////@Component
//public class DecryptBodyFilter implements Filter {
//
//  // 密钥 16位
//  private final String key = "nxfz7f8cf6AO2c+6";
//
//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//    HttpServletRequest request1 = (HttpServletRequest) request;
//    log.info("进入解密过滤器>>>>>>>>>>>>>>{}", request1.getRequestURI());
//    // 拦截非GET请求
//    if (request1.getMethod().equals("GET")) {
//      chain.doFilter(request, response);
//    } else {
//      resetBody(request, request1, response, chain);
//    }
//  }
//
//  public void resetBody(ServletRequest request, HttpServletRequest request1, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//    // 获取body参数
//    String body = SafetyDecryptHttpServlet.getBodyString(request1);
//    String decrypt = null;
//    // 解密
//    log.info("AES加密内容：{}", body);
//    if (!StringUtils.isEmpty(body)) {
//      try {
//        decrypt = AESUtil.aesDecrypt(body, key);
//        log.info("AES解密后内容：{}", decrypt);
//      } catch (Exception e) {
//        log.error("接口BODY参数AES解密失败：{}", e.getMessage());
//      }
//    }
//    if (StringUtils.isEmpty(body)) {
//      chain.doFilter(request, response);
//      return;
//    }
//    SafetyDecryptHttpServlet safetyDecryptHttpServlet = null;
//    try {
//      safetyDecryptHttpServlet = new SafetyDecryptHttpServlet(request1, Objects.requireNonNull(decrypt));
//    } catch (Exception e) {
//      log.error("SafetyDecryptHttpServlet Error:", e);
//    }
//    if (null == safetyDecryptHttpServlet) {
//      chain.doFilter(request, response);
//    } else {
//      chain.doFilter(safetyDecryptHttpServlet, response);
//    }
//  }
//}

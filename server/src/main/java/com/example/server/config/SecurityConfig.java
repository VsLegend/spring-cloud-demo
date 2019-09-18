package com.example.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Wangjunwei
 * @Date 2019/8/7 14:28
 * @Description 关闭自带的跨站域请求伪造保护，若要防止csrf，可以加referer 或者token。二者各有所长
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    super.configure(http);
    http.csrf().disable();
  }
}

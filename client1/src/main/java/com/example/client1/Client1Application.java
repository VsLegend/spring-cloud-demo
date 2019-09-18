package com.example.client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SpringBootApplication
@EnableEurekaClient
public class Client1Application {

  public static void main(String[] args) {
    SpringApplication.run(Client1Application.class, args);
  }

}

package com.example.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Wang Junwei
 * @Date 2022/11/14 10:19
 * @Description 自定义配置
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "custom.property")
public class CustomProperrties {

    private String name;
    private String property1;
    private String property2;

}

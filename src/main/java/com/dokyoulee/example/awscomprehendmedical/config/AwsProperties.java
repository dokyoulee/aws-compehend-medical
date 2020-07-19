package com.dokyoulee.example.awscomprehendmedical.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource({"classpath:aws.yml"})
@ConfigurationProperties
public class AwsProperties {

    private String accessKey;
    private String secretKey;
}

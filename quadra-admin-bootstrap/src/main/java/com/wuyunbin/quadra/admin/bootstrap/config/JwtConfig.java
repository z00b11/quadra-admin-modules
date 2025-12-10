package com.wuyunbin.quadra.admin.bootstrap.config;

import com.wuyunbin.quadra.admin.application.config.JwtProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {
}


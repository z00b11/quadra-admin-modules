package com.wuyunbin.quadra.admin.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {
    private String secret;
    private int ttlSeconds;

    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }
    public int getTtlSeconds() { return ttlSeconds; }
    public void setTtlSeconds(int ttlSeconds) { this.ttlSeconds = ttlSeconds; }
}


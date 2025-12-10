package com.wuyunbin.quadra.admin.bootstrap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.wuyunbin.quadra.admin.adapter.out.persistence")
public class PersistenceConfig {
}


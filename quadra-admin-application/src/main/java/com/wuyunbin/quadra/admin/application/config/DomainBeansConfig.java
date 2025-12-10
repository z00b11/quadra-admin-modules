package com.wuyunbin.quadra.admin.application.config;

import com.wuyunbin.quadra.admin.domain.port.AdminRepository;
import com.wuyunbin.quadra.admin.domain.service.AdminDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 领域服务装配：在应用层提供 AdminDomainService Bean。
 * 说明：保持领域层不依赖 Spring，通过此配置完成依赖注入。
 */
@Configuration
public class DomainBeansConfig {

    @Bean
    public AdminDomainService adminDomainService(AdminRepository repo) {
        return new AdminDomainService(repo);
    }
}


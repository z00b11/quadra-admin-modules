package com.wuyunbin.quadra.admin.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wuyunbin.quadra.admin")
public class QuadraAdminBootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuadraAdminBootstrapApplication.class, args);
	}

}

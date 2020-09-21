package com.github.taoroot.cloud.mall.admin.upms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class AdminUpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminUpmsApplication.class, args);
    }
}

package com.github.taoroot.cloud.mall.v1.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.github.taoroot.cloud.mall.v1.common.mapper")
public class V1AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(V1AdminApplication.class, args);
    }
}

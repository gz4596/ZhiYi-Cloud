package com.github.taoroot.cloud.mall.portal.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PortalPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalPayApplication.class, args);
    }
}

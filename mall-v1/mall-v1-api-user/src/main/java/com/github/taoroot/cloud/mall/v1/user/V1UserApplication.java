package com.github.taoroot.cloud.mall.v1.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class V1UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(V1UserApplication.class, args);
    }
}

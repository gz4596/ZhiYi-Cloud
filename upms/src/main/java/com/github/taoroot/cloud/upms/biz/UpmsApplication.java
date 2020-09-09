package com.github.taoroot.cloud.upms.biz;

import com.github.taoroot.cloud.common.security.config.ResourceServerConfig;
import com.google.common.net.MediaType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestBody;

@Import(ResourceServerConfig.class)
@EnableResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class UpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }
}

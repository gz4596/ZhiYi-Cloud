package com.github.taoroot.cloud.gateway.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Data
@Configuration
@ConfigurationProperties(prefix = "gateway.captcha")
public class CaptchaProperties {

    private final Set<Item> sms = new HashSet<>();

    private final Set<Item> image = new HashSet<>();

    @Data
    static class Item {
        private String path;
        private String paramKey;
        private String paramValue;
    }
}

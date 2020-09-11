package com.github.taoroot.cloud.auth.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务配置,根据不同的client,回调不同的服务,获取 UserInfo
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "auth.user-info")
public class AuthUserServiceProperties {
    private Map<String,Info> route = new HashMap<>();

    @Data
    public static class Info {
        private String token;
        private String path;
    }
}

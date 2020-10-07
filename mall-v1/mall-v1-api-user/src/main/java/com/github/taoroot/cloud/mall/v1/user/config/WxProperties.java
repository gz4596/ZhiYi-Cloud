package com.github.taoroot.cloud.mall.v1.user.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mall.v1.user.wx")
public class WxProperties implements InitializingBean {
    private String appId;
    private String appSecret;
    private Boolean sandbox = false;

    @Override
    public void afterPropertiesSet() {
        assert appId != null;
        assert appSecret != null;
    }
}

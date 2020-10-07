package com.github.taoroot.cloud.mall.v1.user.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 公众号相关配置
 */
@Configuration
@EnableConfigurationProperties(WxProperties.class)
public class WxMpConfig {

    @Resource
    private WxProperties wxProperties;

    /**
     * 微信公众号API的Service
     */
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }


    /**
     * 微信客户端配置存储
     */
    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(wxProperties.getAppId());
        wxMpConfigStorage.setSecret(wxProperties.getAppSecret());
        return wxMpConfigStorage;
    }
}

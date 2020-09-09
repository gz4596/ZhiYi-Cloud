package com.github.taoroot.cloud.common.security.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    protected final RemoteTokenServices remoteTokenServices;

    @Bean
    @Primary
    @LoadBalanced // 添加负责均衡, 跨域使用注册中心的地址解析
    public RestTemplate lbRestTemplate() {
        return new RestTemplate();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        remoteTokenServices.setRestTemplate(lbRestTemplate());
        resources.tokenServices(remoteTokenServices);
    }
}

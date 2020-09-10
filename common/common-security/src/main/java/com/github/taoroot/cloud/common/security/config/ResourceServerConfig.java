package com.github.taoroot.cloud.common.security.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 添加负责均衡
 */
@Configuration
@AllArgsConstructor
@ConditionalOnBean(ResourceServerConfiguration.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    protected final RemoteTokenServices remoteTokenServices;

    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate lbRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // RemoteTokenServices 默认配置, 这里被我们覆盖了,所以我们要还原回去
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            // Ignore 400
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400) {
                    super.handleError(response);
                }
            }
        });
        return restTemplate;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        remoteTokenServices.setRestTemplate(lbRestTemplate());
        remoteTokenServices.setClientId("resource");
        remoteTokenServices.setClientId("secret");
        resources.tokenServices(remoteTokenServices);
    }
}

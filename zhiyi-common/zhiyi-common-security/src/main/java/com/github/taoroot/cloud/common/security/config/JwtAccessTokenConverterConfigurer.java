package com.github.taoroot.cloud.common.security.config;

import com.github.taoroot.cloud.common.security.oauth.AuthUserAuthenticationConverter;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@AllArgsConstructor
@ConditionalOnBean(ResourceServerProperties.class)
public class JwtAccessTokenConverterConfigurer implements org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer {

    private final ResourceServerProperties resourceServerProperties;

    @Override
    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter
                .getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new AuthUserAuthenticationConverter());
        jwtAccessTokenConverter.setSigningKey(resourceServerProperties.getJwt().getKeyValue());  // 配置JWT使用的秘钥
    }
}

package com.github.taoroot.cloud.common.security.config;

import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import com.github.taoroot.cloud.common.security.oauth.AuthUserAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

@ConditionalOnBean(ResourceServerProperties.class)
public class JwtAccessTokenConverterConfigurer implements org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer {

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Autowired(required = false)
    private Map<String, UserDetailsService> userDetailsServiceMap;

    @Override
    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter
                .getAccessTokenConverter();
        AuthUserAuthenticationConverter authUserAuthenticationConverter = new AuthUserAuthenticationConverter();

        // 是否需要自定义查询
        if (userDetailsServiceMap != null) {
            UserDetailsService userDetailsService = userDetailsServiceMap.get(SecurityConstants.REAL_TIME_USER_DETAILS_SERVICE);
            if (userDetailsService != null) {
                authUserAuthenticationConverter.setUserDetailsService(userDetailsService);
            }
        }

        accessTokenConverter.setUserTokenConverter(authUserAuthenticationConverter);
        jwtAccessTokenConverter.setSigningKey(resourceServerProperties.getJwt().getKeyValue());  // 配置JWT使用的秘钥
    }
}


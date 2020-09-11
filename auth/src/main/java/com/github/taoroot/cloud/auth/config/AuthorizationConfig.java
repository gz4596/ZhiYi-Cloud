package com.github.taoroot.cloud.auth.config;

import com.github.taoroot.cloud.auth.service.RemoteUserDetailService;
import com.github.taoroot.cloud.auth.util.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RemoteUserDetailService userDetailsService;

    @Autowired
    private DataSource dataSource;


    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate lbRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 配置 Spring MVC Controller
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // @formatter:off
        endpoints
                .accessTokenConverter(getDefaultAccessTokenConverter()) // 个性化 check_token 返回内容
                .userDetailsService(userDetailsService)   // refresh_token 模式,通过用户名,放回用户信息
                .authenticationManager(authenticationManager); // password 模式, 验证密码, 返回用户登录信息
        // @formatter:on
    }

    /**
     * 配置客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource); // jdbc 读取客户端信息
    }

    /**
     * 配置 Spring Security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // check_token 接口已经被认证服务器代理, 资源服务器不会判断token存在,所以应该直接开发这个接口
        security.checkTokenAccess("permitAll()");
    }

    private DefaultAccessTokenConverter getDefaultAccessTokenConverter() {
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(new DefaultUserAuthenticationConverter() {
            @Override
            public Map<String, ?> convertUserAuthentication(Authentication authentication) {
                Map<String, Object> response = new LinkedHashMap<>(super.convertUserAuthentication(authentication));
                if (authentication.getPrincipal() instanceof AuthUser) {
                    AuthUser principal = (AuthUser) authentication.getPrincipal();
                    response.put("nickname", principal.getNickname());
                    response.putAll(principal.getAttrs());
                }
                return response;
            }
        });
        return defaultAccessTokenConverter;
    }

}

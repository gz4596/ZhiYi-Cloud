package com.github.taoroot.cloud.auth.config;

import com.github.taoroot.cloud.auth.service.RemoteUserDetailService;
import com.github.taoroot.cloud.auth.util.UserDetailsServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.web.client.RestTemplate;

/**
 * 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(UserDetailsServiceProperties.class)
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private RemoteUserDetailService userDetailsService;

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
                .userDetailsService(userDetailsService)   // refresh_token 模式,通过用户名,放回用户信息
                .authenticationManager(authenticationManager); // password 模式, 验证密码, 返回用户登录信息
        // @formatter:on
    }

    /**
     * 配置客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        clients.inMemory()
                    .withClient("mall-admin")
                    .authorizedGrantTypes("password", "refresh_token", "authorization_code")
                    .scopes("all")
                    .secret("{noop}secret") // 密码不加密
                    .and()
                .withClient("resource") // 这是一个资源服务器使用的调取check_token接口,必须携带client和secret所以只需要有账号密码就好
                .secret("{noop}secret")
        ;
        // @formatter:on
    }


    /**
     * 配置 Spring Security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // check_token 接口已经被认证服务器代理, 资源服务器不会判断token存在,所以应该直接开发这个接口
        security.checkTokenAccess("permitAll()");
    }
}

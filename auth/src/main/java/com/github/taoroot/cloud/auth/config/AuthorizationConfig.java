package com.github.taoroot.cloud.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 配置 Spring Security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // check_token 接口已经被认证服务器代理, 资源服务器不会判断token存在,所以应该直接开发这个接口
        security.checkTokenAccess("permitAll()");
    }

    /**
     * 配置客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        clients.inMemory()
                .withClient("client")
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
}

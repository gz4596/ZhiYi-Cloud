package com.github.taoroot.cloud.auth.config;

import com.github.taoroot.cloud.auth.service.RemoteUserDetailService;
import com.github.taoroot.cloud.auth.util.AuthUserServiceProperties;
import com.github.taoroot.cloud.common.security.AuthUser;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.common.security.oauth.AuthUserAuthenticationConverter;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 认证服务器配置
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
@EnableConfigurationProperties(AuthUserServiceProperties.class)
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final RemoteUserDetailService userDetailsService;

    private final DataSource dataSource;

    private final AuthUserServiceProperties authUserServiceProperties;

    /**
     * 配置 Spring MVC Controller
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // @formatter:off
        endpoints
                .tokenEnhancer(tokenEnhancer())
                .accessTokenConverter(jwtAccessTokenConverter())
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
     * 配置JWT的内容增强器
     *
     * @return
     */
    public TokenEnhancer tokenEnhancer() {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtAccessTokenConverter());
        delegates.add((accessToken, authentication) -> {
            if (authentication.getPrincipal() instanceof AuthUser) {
                AuthUser principal = (AuthUser) authentication.getPrincipal();
                Map<String, Object> info = new HashMap<>();
                info.put(SecurityUtils.NICKNAME, principal.getNickname());
                info.putAll(principal.getAttrs());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
            }
            return accessToken;
        });
        enhancerChain.setTokenEnhancers(delegates);
        return enhancerChain;
    }

    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter
                .getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new AuthUserAuthenticationConverter());

        // 代理不同的 client JWT 密钥
        jwtAccessTokenConverter.setSigner(new Signer() {
            final Map<String, Signer> map = new HashMap<>();

            {
                for (String clientId : authUserServiceProperties.getRoute().keySet()) {
                    AuthUserServiceProperties.Info info = authUserServiceProperties.getRoute().get(clientId);
                    map.put(clientId, new MacSigner(info.getJwt()));
                }
            }

            @Override
            public byte[] sign(byte[] bytes) {
                String clientId = SecurityContextHolder.getContext().getAuthentication().getName();
                return map.get(clientId).sign(bytes);
            }

            @Override
            public String algorithm() {
                String clientId = SecurityContextHolder.getContext().getAuthentication().getName();
                return map.get(clientId).algorithm();
            }
        });
        return jwtAccessTokenConverter;
    }

}

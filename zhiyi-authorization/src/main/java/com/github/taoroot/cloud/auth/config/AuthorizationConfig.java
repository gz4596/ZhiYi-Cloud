package com.github.taoroot.cloud.auth.config;

import com.github.taoroot.cloud.auth.service.AuthUserService;
import com.github.taoroot.cloud.auth.service.CacheClientDetailsService;
import com.github.taoroot.cloud.auth.service.JwtSignerService;
import com.github.taoroot.cloud.auth.social.SocialCodeTokenGranter;
import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import com.github.taoroot.cloud.common.core.utils.CaptchaCacheService;
import com.github.taoroot.cloud.common.security.AuthUser;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.common.security.oauth.AuthUserAuthenticationConverter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

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
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final AuthUserService userDetailsService;

    private final JwtSignerService jwtSignerService;

    private final CacheClientDetailsService cacheClientDetailsService;

    private final CaptchaCacheService captchaCacheService;

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
                .authenticationManager(authenticationManager) // password 模式, 验证密码, 返回用户登录信息
                .tokenGranter(tokenGranter(endpoints, authenticationManager));
        // @formatter:on
    }

    /**
     * 配置客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(cacheClientDetailsService); // jdbc 读取客户端信息 (带缓存)
    }

    /**
     * 扩展支持 social 模式, 并且 password 模式添加验证码
     */
    public TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints,
                                     AuthenticationManager authenticationManager) {
        TokenGranter tokenGranter = endpoints.getTokenGranter();
        return (grantType, tokenRequest) -> {
            // 密码模式添加验证码匹配
            if (grantType.equalsIgnoreCase("password")) {
                String key = SecurityUtils.request().getParameter(SecurityConstants.IMAGE_KEY_PARAM);
                String value = SecurityUtils.request().getParameter(SecurityConstants.IMAGE_VALUE_PARAM);
                if (!captchaCacheService.check(key, value)) {
                    throw new InvalidGrantException("验证码错误");
                }
            }
            OAuth2AccessToken grant = tokenGranter.grant(grantType, tokenRequest);
            if (grant != null) {
                return grant;
            }
            return new SocialCodeTokenGranter(
                    authenticationManager,
                    endpoints.getTokenServices(),
                    endpoints.getClientDetailsService(),
                    endpoints.getOAuth2RequestFactory()
            ).grant(grantType, tokenRequest);
        };
    }

    /**
     * 配置JWT的内容增强器
     */
    public TokenEnhancer tokenEnhancer() {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtAccessTokenConverter());
        delegates.add((accessToken, authentication) -> {
            if (authentication.getPrincipal() instanceof AuthUser) {
                AuthUser principal = (AuthUser) authentication.getPrincipal();
                Map<String, Object> info = new HashMap<>();
                info.put(SecurityConstants.NICKNAME, principal.getNickname());
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
        jwtAccessTokenConverter.setSigner(jwtSignerService);
        return jwtAccessTokenConverter;
    }

}

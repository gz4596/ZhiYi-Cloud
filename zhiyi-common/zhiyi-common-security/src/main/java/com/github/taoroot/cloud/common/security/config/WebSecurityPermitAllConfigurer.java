package com.github.taoroot.cloud.common.security.config;

import com.github.taoroot.cloud.common.security.properties.SecurityIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 专门处理不需要 Security 拦截的请求
 */
@Configuration
@Order(1) // 优先级放前面,保证先走这个过滤器链
@EnableConfigurationProperties(SecurityIgnoreProperties.class)
@AllArgsConstructor
@Log4j2
public class WebSecurityPermitAllConfigurer extends WebSecurityConfigurerAdapter {

    private final SecurityIgnoreProperties securityIgnoreProperties;

    /**
     * 配置 HttpSecurity
     */
    protected void configure(HttpSecurity http) throws Exception {

        List<String> permitAllUrls = new ArrayList<>(Arrays.asList("/actuator/**",
                "/v2/api-docs", "/webjars/**", "/**/*.css", "/**/*.js"));

        securityIgnoreProperties.getUrls().forEach(permitAllUrls::addAll);

        log.info("security ignore urls: {}", permitAllUrls);

        // @formatter:off
        http
                .requestMatchers()
                    .antMatchers(permitAllUrls.toArray(new String[0]))
                    .and()
                .csrf().disable()
                .authorizeRequests().anyRequest().permitAll();
        // @formatter:on
    }
}

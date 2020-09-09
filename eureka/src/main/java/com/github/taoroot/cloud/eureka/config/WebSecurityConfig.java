package com.github.taoroot.cloud.eureka.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf().disable() // 关闭csrf, 会导致basic登录失败
                .authorizeRequests().antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .httpBasic();
        // @formatter:off
    }
}

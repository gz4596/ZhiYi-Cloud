package com.github.taoroot.cloud.admin.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .formLogin()
                    .and()
                .authorizeRequests().antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
                    .and()
                .csrf().disable();
        // @formatter:on
    }
}

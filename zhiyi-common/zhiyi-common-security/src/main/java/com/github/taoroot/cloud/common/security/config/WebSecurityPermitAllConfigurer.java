package com.github.taoroot.cloud.common.security.config;

import cn.hutool.core.util.ReUtil;
import com.github.taoroot.cloud.common.security.annotation.NoAuth;
import com.github.taoroot.cloud.common.security.properties.SecurityIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * 专门处理不需要 Security 拦截的请求
 */
@Configuration
@Order(1) // 优先级放前面,保证先走这个过滤器链
@AllArgsConstructor
@Log4j2
@EnableConfigurationProperties(SecurityIgnoreProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityPermitAllConfigurer extends WebSecurityConfigurerAdapter {

    private final SecurityIgnoreProperties securityIgnoreProperties;
    private final ApplicationContext applicationContext;

    /**
     * 配置 HttpSecurity
     */
    protected void configure(HttpSecurity http) throws Exception {
        List<String> permitAllUrls = new LinkedList<>();

        permitAllUrls.addAll(Arrays.asList(
                "/v2/api-docs",
                "/swagger-resources/**",
                "/actuator/**",
                "/webjars/**",
                "/**/*.html",
                "/**/*.htm",
                "/**/*.js",
                "/**/*.jpg",
                "/**/*.jpeg",
                "/**/*.png",
                "/**/*.css")
        );

        permitAllUrls.addAll(securityIgnoreProperties.getDefaultUrls()); // 全局默认
        permitAllUrls.addAll(securityIgnoreProperties.getUrls()); // 模块自定义

        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        // 收集 NoAuth 注解的接口
        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            Set<NoAuth> set = new HashSet<>();
            set.add(AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), NoAuth.class)); // 从类名上获取
            set.add(AnnotationUtils.findAnnotation(handlerMethod.getMethod(), NoAuth.class));  // 从方法上获取

            set.forEach(annotation -> Optional.ofNullable(annotation)
                    .filter(NoAuth::global) // 仅记录全局的
                    .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                            .forEach(url -> permitAllUrls.add(ReUtil.replaceAll(url, "\\{(.*?)\\}", "*")))));
        });


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

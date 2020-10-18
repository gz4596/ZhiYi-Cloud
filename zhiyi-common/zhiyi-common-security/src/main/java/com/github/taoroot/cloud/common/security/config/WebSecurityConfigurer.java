package com.github.taoroot.cloud.common.security.config;

import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.common.security.annotation.PermitAll;
import com.github.taoroot.cloud.common.security.filter.PermitAllValidateFilter;
import com.github.taoroot.cloud.common.security.filter.TenantContextHolderFilter;
import com.github.taoroot.cloud.common.security.properties.SecurityIgnoreProperties;
import com.github.taoroot.cloud.common.security.tenant.TenantDataSourceProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
@Order(Integer.MIN_VALUE) // 优先级放前面,保证先走这个过滤器链
@AllArgsConstructor
@Log4j2
@EnableConfigurationProperties({SecurityIgnoreProperties.class, TenantDataSourceProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({TenantContextHolderFilter.class})
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final SecurityIgnoreProperties securityIgnoreProperties;
    private final ApplicationContext applicationContext;

    protected void configure(HttpSecurity http) throws Exception {
        PermitAllValidateFilter permitAllValidateFilter = new PermitAllValidateFilter();
        permitAllValidateFilter.setKey(securityIgnoreProperties.getKey());
        SecurityUtils.setKey(securityIgnoreProperties.getKey());

        requestMatchers(defaultUrls(), http); // 系统配置
        requestMatchers(securityIgnoreProperties.getDefaultUrls(), http); // 全局配置
        requestMatchers(securityIgnoreProperties.getUrls(), http); // 局部配置
        // 注解配置
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            HandlerMethod handlerMethod = map.get(info);
            PermitAll annotation = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), PermitAll.class);
            if (annotation == null) {
                annotation = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), PermitAll.class);
            }
            if (annotation != null) {
                for (RequestMethod method : info.getMethodsCondition().getMethods()) {
                    String[] urls = info.getPatternsCondition().getPatterns().toArray(new String[0]);
                    http.requestMatchers().antMatchers(HttpMethod.valueOf(method.name()), urls);
                    if (!annotation.global()) {
                        for (String pattern : urls) {
                            permitAllValidateFilter.antPathMatcher(new AntPathRequestMatcher(pattern, method.name()));
                        }
                    }
                }
            }
        }

        // @formatter:off
        http
                .csrf().disable()
                .addFilterBefore(permitAllValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().permitAll();
        // @formatter:on
    }

    public static List<String> defaultUrls() {
        return Arrays.asList("/v2/api-docs:GET",
                "/swagger-resources/**:GET",
                "/actuator/**:GET",
                "/webjars/**:GET",
                "/**/*.html:GET",
                "/**/*.htm:GET",
                "/**/*.js:GET",
                "/**/*.jpg:GET",
                "/**/*.jpeg:GET",
                "/**/*.png:GET",
                "/**/*.css:GET");
    }

    public static void requestMatchers(List<String> args, HttpSecurity httpSecurity) {
        args.forEach(item -> {
            if (item.contains(":")) {
                String[] split = item.split(":");
                httpSecurity.requestMatchers().antMatchers(split[1], split[0]);
            } else {
                httpSecurity.requestMatchers().antMatchers(item);
            }
        });
    }
}

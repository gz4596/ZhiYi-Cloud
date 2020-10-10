package com.github.taoroot.cloud.gateway.captcha;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;

/**
 * 验证码
 */
public class CaptchaFilter extends AbstractGatewayFilterFactory<Object> {

    private CaptchaProperties captchaProperties;

    @Override
    public GatewayFilter apply(Object config) {
        return new GatewayFilter() {

            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();

                AntPathMatcher antPathMatcher = new AntPathMatcher();

                // 图形验证码
                boolean image = check(captchaProperties.getImage(), request, antPathMatcher);

                if (image) {
                    throw new RuntimeException("验证码不合法");
                }

                return chain.filter(exchange);
            }


            private boolean check(Set<CaptchaProperties.Item> itemSet, ServerHttpRequest request, AntPathMatcher antPathMatcher) {
                List<CaptchaProperties.Item> collect = itemSet.stream()
                        .filter(item -> antPathMatcher.match(item.getPath(), request.getURI().getPath()))
                        .collect(Collectors.toList());

                if (collect.size() == 0) {
                    return true;
                }

                return collect.stream()
                        .filter(item -> antPathMatcher.match(item.getPath(), request.getURI().getPath()))
                        .anyMatch(item -> {
                            // (仅匹配url) paramKey=null, paramValue=null
                            if (StringUtils.isEmpty(item.getParamKey()) && StringUtils.isEmpty(item.getParamValue())) {
                                return true;
                            }
                            // (仅匹配参数是否存在) paramKey="xxx", paramValue=null
                            if (!StringUtils.isEmpty(item.getParamKey()) && StringUtils.isEmpty(item.getParamValue())) {
                                return request.getQueryParams().containsKey(item.getParamKey());
                            }
                            // (匹配参数是否值相同) paramKey="xxx", paramValue="xxx"
                            if (!StringUtils.isEmpty(item.getParamKey()) && !StringUtils.isEmpty(item.getParamValue())) {
                                String first = request.getQueryParams().getFirst(item.getParamKey());
                                return item.getParamValue().equalsIgnoreCase(first);
                            }
                            return false;
                        });
            }


            @Override
            public String toString() {
                return filterToStringCreator(CaptchaFilter.this).toString();
            }
        };
    }


}

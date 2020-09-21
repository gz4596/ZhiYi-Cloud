package com.github.taoroot.cloud.gateway.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Component
@Primary
public class SwaggerResourceConfig implements SwaggerResourcesProvider {
    protected static final String API_URI = "/v2/api-docs";

    @Autowired
    private GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get() {
        return gatewayProperties.getRoutes()
                .stream()
                // 需要加入 swagger api 的 路由
                .filter(route -> route.getFilters().stream().anyMatch(filter -> filter.getName().equals(SwaggerHeaderFilter.class.getSimpleName())))

                .map(route ->
                {
                    Optional<PredicateDefinition> path = route.getPredicates().stream().filter(predicate -> predicate.getName().equals("Path")).findFirst();
                    if (!path.isPresent()) {
                        return null;
                    }
                    String uri = path.get().getArgs().values().iterator().next();
                    String name = route.getUri().getHost().toLowerCase();
                    SwaggerResource swaggerResource = new SwaggerResource();
                    swaggerResource.setUrl(uri.replace("/**", API_URI));
                    swaggerResource.setName(name);
                    swaggerResource.setSwaggerVersion("2.0");
                    return swaggerResource;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}

package com.github.taoroot.cloud.common.security.tenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = TenantDataSourceProperties.PREFIX)
public class TenantDataSourceProperties {
    public static final String PREFIX = "spring.datasource.dynamic.tenant";

    private String prefix = "tenant_";

    private Boolean enable = true;
}

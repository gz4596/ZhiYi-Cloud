package com.github.taoroot.cloud.common.security.tenant;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public class TenantContextHolder {

    private final ThreadLocal<Integer> TENANT_THREAD_LOCAL = new ThreadLocal<>();

    public void set(Integer tenantId) {
        TenantDataSourceProperties tenantDataSourceProperties =
                SpringUtil.getApplicationContext().getBean(TenantDataSourceProperties.class);
        DynamicDataSourceContextHolder.clear();
        DynamicDataSourceContextHolder.push(tenantDataSourceProperties.getPrefix() + tenantId);
        TENANT_THREAD_LOCAL.set(tenantId);
    }

    public Integer get() {
        return TENANT_THREAD_LOCAL.get();
    }

    public void clear() {
        TENANT_THREAD_LOCAL.remove();
        DynamicDataSourceContextHolder.clear();
    }
}

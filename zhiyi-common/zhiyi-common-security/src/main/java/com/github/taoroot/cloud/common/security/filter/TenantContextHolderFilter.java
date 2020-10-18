package com.github.taoroot.cloud.common.security.filter;

import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import com.github.taoroot.cloud.common.core.datascope.DataScopeContextHolder;
import com.github.taoroot.cloud.common.security.tenant.TenantContextHolder;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class TenantContextHolderFilter extends GenericFilterBean {

    RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String tenant = request.getHeader(SecurityConstants.TENANT_ID);
        if (!StringUtils.isEmpty(tenant)) {
            TenantContextHolder.set(Integer.valueOf(tenant));
        }
        tenant = request.getParameter(SecurityConstants.TENANT_ID);
        if (!StringUtils.isEmpty(tenant)) {
            TenantContextHolder.set(Integer.valueOf(tenant));
        }

        // 解决URL跳转,导致线程改变
        if (TenantContextHolder.get() == null) {
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if (savedRequest != null) {
                String[] params = savedRequest.getParameterMap().get(SecurityConstants.TENANT_ID);
                if (params != null && params.length > 0) {
                    TenantContextHolder.set(Integer.valueOf(params[0]));
                }
            }
        }

        filterChain.doFilter(request, response);

        TenantContextHolder.clear();
        DataScopeContextHolder.clear();
    }
}

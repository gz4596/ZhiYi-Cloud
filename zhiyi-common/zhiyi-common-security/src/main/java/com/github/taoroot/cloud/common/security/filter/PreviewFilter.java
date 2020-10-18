package com.github.taoroot.cloud.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.tenant.TenantContextHolder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class PreviewFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String preview = request.getHeader("preview_tenant");
        if (preview != null) {
            String[] ids = preview.split(",");
            List<Integer> collect = Arrays.stream(ids).map(Integer::new).collect(Collectors.toList());
            Integer tenantId = TenantContextHolder.get();
            boolean b = collect.stream().anyMatch(item -> item.equals(tenantId));
            if (b) {
                if (request.getMethod().equals("DELETE")
                        || request.getMethod().equals("PUT")
                        || request.getMethod().equals("POST")) {
                    unsuccessfulAuthentication(response);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    protected void unsuccessfulAuthentication(HttpServletResponse response)
            throws IOException, ServletException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        R<String> r = R.errMsg("当前租户仅限预览");
        response.getWriter().write(new ObjectMapper().writeValueAsString(r));
    }
}

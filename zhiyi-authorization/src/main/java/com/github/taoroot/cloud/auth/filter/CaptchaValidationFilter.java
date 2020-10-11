package com.github.taoroot.cloud.auth.filter;

import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import com.github.taoroot.cloud.common.core.utils.CaptchaCacheService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class CaptchaValidationFilter extends OncePerRequestFilter {

    private final AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher("/login", "POST");

    @Setter
    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler("/login?error");

    @Autowired
    private CaptchaCacheService captchaCacheService;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = antPathMatcher.matches(request);

        if (action) {
            try {
                validate(request);
            } catch (AuthenticationException e) {
                log.error("验证码错误", e);
                unsuccessfulAuthentication(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString(), failed);
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
            logger.debug("Delegating to authentication failure handler " + failureHandler);
        }


        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    private void validate(HttpServletRequest request) {
        String key = obtainKey(request);
        String code = obtainCode(request);

        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(code)) {
            throw new BadCredentialsException("验证码不能为空");
        }

        String cacheCode = captchaCacheService.get(key);

        if (cacheCode == null) {
            throw new BadCredentialsException("验证码已失效");
        }

        if (!code.toLowerCase().equals(cacheCode)) {
            throw new BadCredentialsException("验证码错误");
        }
    }

    private String obtainKey(HttpServletRequest request) {

        return request.getParameter(SecurityConstants.IMAGE_KEY_PARAM);
    }

    private String obtainCode(HttpServletRequest request) {
        return request.getParameter(SecurityConstants.IMAGE_VALUE_PARAM);
    }
}

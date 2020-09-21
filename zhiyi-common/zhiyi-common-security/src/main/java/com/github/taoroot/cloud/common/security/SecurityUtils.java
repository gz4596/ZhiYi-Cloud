package com.github.taoroot.cloud.common.security;

import com.github.taoroot.cloud.common.core.datascope.DataScope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class SecurityUtils {
    public static final String ROLE_IDS = "role_ids";
    public static final String DEPT_ID = "dept_id";
    public static final String NICKNAME = "nickname";

    public static Integer userId() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public static DataScope dataScope() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            Object principal = oAuth2Authentication.getPrincipal();
            if (principal instanceof AuthUser) {
                AuthUser authUser = (AuthUser) principal;
                DataScope dataScope = new DataScope();
                dataScope.setUserId(userId());
                dataScope.setRoleIds(authUser.getRoleIds());
                return dataScope;
            }
        }
        return null;
    }
}

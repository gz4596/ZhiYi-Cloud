package com.github.taoroot.cloud.common.security;

import com.github.taoroot.cloud.common.core.datascope.DataScope;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Log4j2
public class SecurityUtils {

    public static Integer userId() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if ("anonymousUser".equals(name)) {
            log.debug("not found userId, userId() return -1");
            return -1;
        }
        return Integer.parseInt(name);
    }

    public static DataScope dataScope() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getDataScope(authentication);
    }

    public static DataScope getDataScope(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthUser) {
            AuthUser authUser = (AuthUser) principal;
            DataScope dataScope = new DataScope();
            dataScope.setUserId(Integer.valueOf(authUser.getUsername()));
            dataScope.setRoleIds(authUser.getRoleIds());
            return dataScope;
        }
        return null;
    }

    public static AuthUser getAuthUser(AuthUserInfo userInfo) {
        if (userInfo == null) {
            return null;
        }
        AuthUser user = new AuthUser(
                userInfo.getUsername(),
                userInfo.getPassword() == null ? "N/A" : userInfo.getPassword(),
                userInfo.isEnabled(),
                AuthorityUtils.createAuthorityList(userInfo.getAuthorities()));

        if (StringUtils.isEmpty(userInfo.getNickname())) {
            user.setNickname(userInfo.getUsername());
        } else {
            user.setNickname(userInfo.getNickname());
        }
        user.setAttrs(userInfo.getAttrs());
        user.setDeptId(user.getDeptId());
        user.setRoleIds(userInfo.getRoleIds());
        return user;
    }

    public static HttpServletRequest request() {
        return ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}

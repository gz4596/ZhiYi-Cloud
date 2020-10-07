package com.github.taoroot.cloud.common.security;

import com.github.taoroot.cloud.common.core.datascope.DataScope;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

public class SecurityUtils {
    public static final String ROLE_IDS = "role_ids";
    public static final String DEPT_ID = "dept_id";
    public static final String NICKNAME = "nickname";

    public static Integer userId() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
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
}

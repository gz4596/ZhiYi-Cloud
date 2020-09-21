package com.github.taoroot.cloud.common.security.oauth;

import com.github.taoroot.cloud.common.security.AuthUser;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * AuthUser 内容的序列化 和 反序列化
 */
public class AuthUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>(super.convertUserAuthentication(authentication));
        if (authentication.getPrincipal() instanceof AuthUser) {
            AuthUser principal = (AuthUser) authentication.getPrincipal();
            response.put(SecurityUtils.DEPT_ID, principal.getDeptId());
            response.put(SecurityUtils.ROLE_IDS, principal.getRoleIds());
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        Authentication authentication = super.extractAuthentication(map);
        AuthUser authUser = new AuthUser(authentication.getName(), authentication.getAuthorities());
        List<Integer> roleIds = new ArrayList<>();
        Object obj = map.get(SecurityUtils.ROLE_IDS);
        if (obj instanceof ArrayList<?>) {
            roleIds = ((List<?>) obj).stream().map(o -> (Integer) o).collect(Collectors.toList());
        }
        authUser.setRoleIds(roleIds);
        authUser.setDeptId((Integer) map.get(SecurityUtils.DEPT_ID));
        return new UsernamePasswordAuthenticationToken(authUser, "N/A", authentication.getAuthorities());
    }
}

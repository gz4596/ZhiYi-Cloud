package com.github.taoroot.cloud.common.security.oauth;

import com.github.taoroot.cloud.common.security.AuthUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * check_token 具体内容
 */
public class AuthUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    public static final String NICKNAME = "nickname";
    public static final String ROLE_IDS = "role_ids";


    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>(super.convertUserAuthentication(authentication));
        if (authentication.getPrincipal() instanceof AuthUser) {
            AuthUser principal = (AuthUser) authentication.getPrincipal();
            response.put(NICKNAME, principal.getNickname());
            response.put(ROLE_IDS, principal.getRoleIds());
            response.putAll(principal.getAttrs());
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        Authentication authentication = super.extractAuthentication(map);
        AuthUser authUser = new AuthUser(authentication.getName(), authentication.getAuthorities());
        authUser.setRoleIds((List<Integer>) map.get(ROLE_IDS));

        return new UsernamePasswordAuthenticationToken(authUser, "N/A", authentication.getAuthorities());
    }
}

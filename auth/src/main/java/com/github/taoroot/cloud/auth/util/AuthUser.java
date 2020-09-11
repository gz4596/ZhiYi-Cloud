package com.github.taoroot.cloud.auth.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * username 存 userId
 */
@Getter
@Setter
public class AuthUser extends User {
    // 昵称
    private String nickname;
    // 额外描述
    private Map<String, Object> attrs = new HashMap<>();

    public AuthUser(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, true, true, true, authorities);
    }
}

package com.github.taoroot.cloud.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SecurityUtils {

    public static Integer userId() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public static List<String> authorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null;
    }

    public static List<String> roles() {
        return Objects.requireNonNull(SecurityUtils.authorities())
                .stream().filter(authority -> authority.startsWith("ROLE_"))
                .collect(Collectors.toList());
    }
}

package com.github.taoroot.cloud.common.security;

import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import com.github.taoroot.cloud.common.core.datascope.DataScope;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.tenant.TenantContextHolder;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Log4j2
public class SecurityUtils {

    @Setter
    private static String key;

    public static Integer userId() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if ("anonymousUser".equals(name)) {
            log.debug("not found userId, userId() return -1");
            return -1;
        }
        return Integer.parseInt(name);
    }

    public static AuthUser userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthUser) {
            return (AuthUser) principal;
        }
        return null;
    }

    public static DataScope dataScope() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getDataScope(authentication);
    }

    public static HttpServletRequest request() {
        return ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static HttpHeaders httpHeaders() {
        HttpServletRequest request = request();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstants.INTERNAL_AUTHORIZATION, SecurityUtils.innerAuthorization());
        httpHeaders.add(SecurityConstants.TENANT_ID, String.valueOf(TenantContextHolder.get()));
        httpHeaders.add("X-Forwarded-For", request.getHeader("X-Forwarded-For"));
        httpHeaders.add("X-Forwarded-Host", request.getHeader("X-Forwarded-Host"));
        httpHeaders.add("X-Real-IP", request.getHeader("X-Real-IP"));
        return httpHeaders;
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
        user.setDeptId(userInfo.getDeptId());
        user.setRoleIds(userInfo.getRoleIds());
        return user;
    }

    public static String innerAuthorization() {
        long expireTime = System.currentTimeMillis() + 5 * 60 * 1000;
        String[] strings = new String[3];
        strings[0] = UUID.randomUUID().toString().substring(0, 5);
        strings[1] = String.valueOf(expireTime);
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        strings[2] = new String(Hex.encode(digest.digest((strings[0] + strings[1] + key).getBytes())));
        return encodeCookie(strings);
    }

    protected static String encodeCookie(String[] cookieTokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cookieTokens.length; i++) {
            try {
                sb.append(URLEncoder.encode(cookieTokens[i], StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }

            if (i < cookieTokens.length - 1) {
                sb.append(":");
            }
        }

        String value = sb.toString();

        sb = new StringBuilder(new String(Base64.getEncoder().encode(value.getBytes())));

        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}

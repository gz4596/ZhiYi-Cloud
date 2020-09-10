package com.github.taoroot.cloud.common.core.vo;

import lombok.Data;

/**
 * 统一用户信息格式
 */
@Data
public class AuthUserInfo {
    private String userId;
    private String username;
    private String password;
    private String[] roles;
    private String[] authorities;
}

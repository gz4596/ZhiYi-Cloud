package com.github.taoroot.cloud.common.core.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一用户信息格式
 */
@Data
public class AuthUserInfo {
    private String nickname;
    private String username;
    private String password;
    private boolean enabled = true;
    private String[] authorities = new String[]{};
    private Map<String, Object> attrs = new HashMap<>();
}

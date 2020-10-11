package com.github.taoroot.cloud.common.core.vo;

import lombok.Data;

@Data
public class AuthSocialInfo {
    private String type;
    private String title;
    private String authorizeUri;
    private String icon;
}

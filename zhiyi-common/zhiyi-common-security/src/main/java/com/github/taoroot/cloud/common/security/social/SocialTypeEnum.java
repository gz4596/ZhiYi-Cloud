package com.github.taoroot.cloud.common.security.social;

public interface SocialTypeEnum {
    String SMS = "SMS";
    String QQ = "QQ";
    String GITHUB = "GITHUB";
    String WX_MP = "WX_MP";
    String WX_OPEN = "WX_OPEN";
    String WX_OPEN_AUTHORIZATION_CODE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    String GITEE = "GITEE";
    String GITEE_AUTHORIZATION_CODE_URL = "https://gitee.com/oauth/token?grant_type=authorization_code&code=%S&client_id=%s&redirect_uri=" + "%s&client_secret=%s";
}

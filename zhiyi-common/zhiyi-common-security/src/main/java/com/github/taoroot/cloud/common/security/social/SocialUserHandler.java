package com.github.taoroot.cloud.common.security.social;


import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;

public interface SocialUserHandler {

    Boolean checkParams(String code, String redirectUri);

    String getToken(String code, String redirectUri);

    SocialUser loadSocialUser(String token);

    AuthUserInfo loadAuthUserInfo(SocialUser socialUser);

    String bindAuthUserInfo(SocialUser socialUser);

    AuthUserInfo login(String code, String redirectUri);

    String bind(String code, String redirectUri);
}

package com.github.taoroot.cloud.common.security.social;


import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;

public interface SocialLoginHandler {


    Boolean checkParams(String code, String redirectUri);

    String getToken(String code, String redirectUri);

    SocialUser loadSocialUser(String token);

    AuthUserInfo loadAuthUserInfo(SocialUser socialUser);

    AuthUserInfo handle(String code, String redirectUri);

}

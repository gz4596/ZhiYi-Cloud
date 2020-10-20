package com.github.taoroot.cloud.common.security.social;

import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;

public abstract class AbstractSocialLoginHandler implements SocialLoginHandler {

    @Override
    public Boolean checkParams(String code, String redirectUri) {
        return true;
    }

    @Override
    public AuthUserInfo handle(String code, String redirectUri) {
        if (!checkParams(code, redirectUri)) {
            return null;
        }

        String token = getToken(code, redirectUri);

        if (token == null) {
            return null;
        }

        SocialUser socialUser = loadSocialUser(token);

        if (socialUser == null) {
            return null;
        }

        return loadAuthUserInfo(socialUser);
    }
}

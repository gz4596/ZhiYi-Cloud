package com.github.taoroot.cloud.mall.v1.admin.service;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.vo.AuthSocialInfo;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;

import java.util.List;

public interface AuthService {
    R<Object> userInfo();

    AuthUserInfo authByUsername(String username);

    List<AuthSocialInfo> socials(String redirectUrl, Boolean isProxy, String type);

    R<String> unbind(Integer id);

    R updatePass(String oldPass, String newPass);
}

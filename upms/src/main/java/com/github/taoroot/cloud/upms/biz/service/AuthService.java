package com.github.taoroot.cloud.upms.biz.service;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;

public interface AuthService {
    R userInfo();

    AuthUserInfo authByUsername(String username);
}

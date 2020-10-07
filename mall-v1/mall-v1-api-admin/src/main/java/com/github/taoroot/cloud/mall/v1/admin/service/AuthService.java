package com.github.taoroot.cloud.mall.v1.admin.service;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;

public interface AuthService {
    R userInfo();

    AuthUserInfo authByUsername(String username);
}

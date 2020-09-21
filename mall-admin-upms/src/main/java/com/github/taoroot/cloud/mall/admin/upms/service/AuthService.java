package com.github.taoroot.cloud.mall.admin.upms.service;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;

public interface AuthService {
    R userInfo();

    AuthUserInfo authByUsername(String username);
}

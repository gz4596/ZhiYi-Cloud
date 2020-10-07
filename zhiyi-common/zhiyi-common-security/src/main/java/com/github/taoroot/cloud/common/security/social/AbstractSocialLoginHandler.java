package com.github.taoroot.cloud.common.security.social;

import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import org.springframework.util.StringUtils;

public abstract class AbstractSocialLoginHandler implements SocialLoginHandler {

    /***
     * 数据合法性校验
     * @param code 通过用户传入获取唯一标识
     * @return 默认不校验
     */
    @Override
    public Boolean check(String code) {
        return true;
    }

    /**
     * 处理方法
     *
     * @param code 登录参数
     * @return
     */
    @Override
    public AuthUserInfo handle(String code) {
        if (!check(code)) {
            return null;
        }

        String identify = identify(code);

        if (StringUtils.isEmpty(identify)) {
            return null;
        }

        return info(identify);
    }
}

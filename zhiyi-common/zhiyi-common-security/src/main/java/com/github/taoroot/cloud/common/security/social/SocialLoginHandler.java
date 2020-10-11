package com.github.taoroot.cloud.common.security.social;


import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;

/**
 * 第三方授权登录处理器
 */
public interface SocialLoginHandler {

    /***
     * 数据合法性校验
     * @param code 通过用户传入获取唯一标识
     */
    Boolean check(String code);

    /**
     * 获取第三方的账号内容
     */
    String identify(String code);

    /**
     * 通过唯一标识获取用户信息
     */
    AuthUserInfo info(String identify);

    /**
     * 处理方法
     */
    AuthUserInfo handle(String code);

}

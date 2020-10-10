package com.github.taoroot.cloud.common.core.constant;

public interface SecurityConstants {
    /**
     * 内部
     */
    String FROM_IN = "Y";

    /**
     * 标志
     */
    String FROM = "from";

    /**
     * 实时查询用户接口
     */
    String REAL_TIME_USER_DETAILS_SERVICE = "realTimeUserDetailsService";


    String AUTH_USER_PATH = "userAuthPath";

    String AUTH_SOCIAL_PATH = "socialAuthPath";

    String AUTH_JWT_SECRET = "jwt";

    String ROLE_IDS = "role_ids";
    String DEPT_ID = "dept_id";
    String TENANT_ID = "tenant_id";
    String NICKNAME = "nickname";

    /**
     * 授权码模式code key 前缀
     */
    String OAUTH_CODE_PREFIX = "oauth:code:";
}

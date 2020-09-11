package com.github.taoroot.cloud.upms.biz.web;

import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.upms.biz.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authservice;

    /**
     * 特供 Auth-Server 回调查询
     *
     * @param username 用户登录账号
     */
    @SneakyThrows
    @GetMapping(value = "/auth/user_info_by_auth")
    public AuthUserInfo authByUsername(String username, String client, String token) {
        if (!"zhiyi-cloud".equals(token)) {
            return null;
        }
        return authservice.authByUsername(username);
    }
}

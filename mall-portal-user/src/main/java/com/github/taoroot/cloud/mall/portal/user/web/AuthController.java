package com.github.taoroot.cloud.mall.portal.user.web;

import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@AllArgsConstructor
public class AuthController {

    /**
     * 特供 Auth-Server 回调查询
     *
     * @param username 用户登录账号
     */
    @SneakyThrows
    @GetMapping(value = "/auth/user_info_by_auth")
    public AuthUserInfo authByUsername(String username, String type, String client, String token) {
        if (!"zhiyi-cloud".equals(token)) {
            return null;
        }

        AuthUserInfo userInfo = new AuthUserInfo();
        userInfo.setUsername("1");
        userInfo.setPassword("{bcrypt}$2a$10$F.8BYXYzdKEGXPEZ0WzzR.21DWMZyzsg3nEsc3ZNHoJ/GR3A71ETm");
        userInfo.setAuthorities(new String[]{"ROLE_USER"});
        return userInfo;
    }
}


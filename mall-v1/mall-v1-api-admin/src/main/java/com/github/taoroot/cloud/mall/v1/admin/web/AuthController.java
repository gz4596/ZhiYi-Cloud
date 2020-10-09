package com.github.taoroot.cloud.mall.v1.admin.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.annotation.Log;
import com.github.taoroot.cloud.common.security.annotation.NoAuth;
import com.github.taoroot.cloud.mall.v1.admin.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authservice;

    /**
     * 特供 Auth-Server 回调查询
     *
     * @param username 用户登录账号
     */
    @Log(value = "账号登录")
    @NoAuth
    @SneakyThrows
    @ApiIgnore
    @GetMapping(value = "/auth/username")
    public AuthUserInfo authByUsername(String username) {
        return authservice.authByUsername(username);
    }

    @ApiOperation("获取用户信息")
    @SneakyThrows
    @GetMapping(value = "/auth/user_info")
    public R userInfo() {
        return authservice.userInfo();
    }
}

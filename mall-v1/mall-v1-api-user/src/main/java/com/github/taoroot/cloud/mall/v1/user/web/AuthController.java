package com.github.taoroot.cloud.mall.v1.user.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.annotation.NoAuth;
import com.github.taoroot.cloud.common.security.social.SocialLoginHandler;
import com.github.taoroot.cloud.mall.v1.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@Log4j2
@RestController
@AllArgsConstructor
public class AuthController {

    private final Map<String, SocialLoginHandler> socialLoginHandler;
    private final UserService userService;

    /**
     * 特供 Auth-Server 回调查询
     */
    @NoAuth
    @SneakyThrows
    @ApiIgnore
    @GetMapping(value = "/auth/social")
    public AuthUserInfo userInfoBySocial(String type, String code) {
        SocialLoginHandler socialLoginHandler = this.socialLoginHandler.get(type.toLowerCase());
        if (socialLoginHandler == null) {
            socialLoginHandler = this.socialLoginHandler.get(type.toUpperCase());
        }
        if (socialLoginHandler == null) {
            log.error("SocialLoginHandler 不存在 {}", type);
            return null;
        }
        return socialLoginHandler.handle(code);
    }

    @ApiOperation("获取用户信息")
    @SneakyThrows
    @GetMapping(value = "/auth/user_info")
    public R userInfo() {
        return userService.userInfo();
    }
}


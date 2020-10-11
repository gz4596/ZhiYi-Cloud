package com.github.taoroot.cloud.mall.v1.admin.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.vo.AuthSocialInfo;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.annotation.Log;
import com.github.taoroot.cloud.common.security.annotation.NoAuth;
import com.github.taoroot.cloud.common.security.social.SocialLoginHandler;
import com.github.taoroot.cloud.mall.v1.admin.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authservice;
    private final Map<String, SocialLoginHandler> socialLoginHandler;

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
    public R<AuthUserInfo> authByUsername(String username) {
        return R.ok(authservice.authByUsername(username));
    }

    /**
     * 特供 Auth-Server 回调查询
     */
    @NoAuth
    @SneakyThrows
    @ApiIgnore
    @GetMapping(value = "/auth/socials")
    public R<List<AuthSocialInfo>> socials(String redirectUri) {
        return R.ok(authservice.socials(redirectUri));
    }

    /**
     * 特供 Auth-Server 回调查询
     */
    @NoAuth
    @SneakyThrows
    @ApiIgnore
    @GetMapping(value = "/auth/social")
    public R<AuthUserInfo> userInfoBySocial(String type, String code) {
        SocialLoginHandler socialLoginHandler = this.socialLoginHandler.get(type.toLowerCase());
        if (socialLoginHandler == null) {
            socialLoginHandler = this.socialLoginHandler.get(type.toUpperCase());
        }
        if (socialLoginHandler == null) {
            log.error("SocialLoginHandler 不存在 {}", type);
            return R.errMsg(type + " 不存在");
        }
        return R.ok(socialLoginHandler.handle(code));
    }


    @ApiOperation("获取用户信息")
    @SneakyThrows
    @GetMapping(value = "/auth/user_info")
    public R userInfo() {
        return authservice.userInfo();
    }
}

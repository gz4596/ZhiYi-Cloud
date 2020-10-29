package com.github.taoroot.cloud.mall.v1.user.web;

import cn.hutool.core.lang.Assert;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.annotation.PermitAll;
import com.github.taoroot.cloud.common.security.social.SocialUserHandler;
import com.github.taoroot.cloud.mall.v1.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@Log4j2
@RestController
@AllArgsConstructor
public class AuthController {

    private final Map<String, SocialUserHandler> socialUserHandlerMap;
    private final UserService userService;

    /**
     * 特供 Auth-Server 回调查询
     */
    @PermitAll
    @SneakyThrows
    @ApiIgnore
    @GetMapping(value = "/auth/social")
    public R<AuthUserInfo> userInfoBySocial(String type, String code,
                                            @RequestParam(required = false, value = "redirect_uri") String redirectUri) {
        SocialUserHandler socialUserHandler = this.socialUserHandlerMap.get(type.toLowerCase());
        Assert.notNull(socialUserHandler, "不支持当前社交");
        return R.ok(socialUserHandler.login(code, redirectUri));
    }

    @ApiOperation("获取用户信息")
    @SneakyThrows
    @GetMapping(value = "/auth/user_info")
    public R userInfo() {
        return userService.userInfo();
    }
}


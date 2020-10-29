package com.github.taoroot.cloud.mall.v1.admin.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.vo.AuthSocialInfo;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.annotation.Log;
import com.github.taoroot.cloud.common.security.annotation.PermitAll;
import com.github.taoroot.cloud.common.security.social.SocialUserHandler;
import com.github.taoroot.cloud.mall.v1.admin.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@AllArgsConstructor
@Api(tags = "登录管理")
public class AuthController {

    private final AuthService authservice;
    private final Map<String, SocialUserHandler> socialUserHandlerMap;

    /**
     * 特供 Auth-Server 回调查询
     *
     * @param username 用户登录账号
     */
    @Log("账号登录")
    @PermitAll(global = false)
    @SneakyThrows
    @ApiIgnore
    @GetMapping(value = "/auth/username")
    public R<AuthUserInfo> authByUsername(String username) {
        return R.ok(authservice.authByUsername(username));
    }

    /**
     * 特供 Auth-Server 回调查询
     */
    @Log("社交登录地址")
    @PermitAll
    @SneakyThrows
    @GetMapping(value = "/auth/socials")
    public R<List<AuthSocialInfo>> socials(@RequestParam("redirect_uri") String redirectUri,
                                           @RequestParam(defaultValue = "true") Boolean isProxy) {
        return R.ok(authservice.socials(redirectUri, isProxy, null));
    }

    /**
     * 特供 Auth-Server 回调查询
     */
    @PermitAll
    @Log("社交登录")
    @ApiIgnore
    @GetMapping(value = "/auth/social")
    public R<AuthUserInfo> userInfoBySocial(String type, String code,
                                            @RequestParam(required = false, value = "redirect_uri") String redirectUri) {
        SocialUserHandler socialUserHandler = this.socialUserHandlerMap.get(type.toUpperCase());
        Assert.notNull(socialUserHandler, "不支持登录类型");
        return R.ok(socialUserHandler.login(code, redirectUri));
    }


    /**
     * 绑定地址
     */
    @Log("社交绑定地址")
    @SneakyThrows
    @GetMapping(value = "/bind/social/uri")
    public R<String> bindSocialUrl(@RequestParam("redirect_uri") String redirectUri,
                                           @RequestParam(defaultValue = "true") Boolean isProxy,
                                           String type) {
        return R.ok(authservice.socials(redirectUri, isProxy, type).get(0).getAuthorizeUri());
    }

    @Log("社交账号绑定")
    @PostMapping(value = "/bind/social")
    public R<String> socialBind(String type, String code,
                                @RequestParam(required = false, value = "redirect_uri") String redirectUri) {
        SocialUserHandler socialUserHandler = this.socialUserHandlerMap.get(type.toUpperCase());
        Assert.notNull(socialUserHandler, "不支持登录类型");
        return R.okMsg(socialUserHandler.bind(code, redirectUri));
    }

    @Log("社交账号解除绑定")
    @DeleteMapping(value = "/unbind/social/{id}")
    public R<String> socialUnbind(@PathVariable Integer id) {
        return authservice.unbind(id);
    }


    @Log("修改密码")
    @ApiOperation("用户信息")
    @PutMapping(value = "/auth/password")
    public R updatePass(String oldPass, String newPass) {
        return authservice.updatePass(oldPass, newPass);
    }

    @Log("用户详情")
    @ApiOperation("用户信息")
    @GetMapping(value = "/auth/user_info")
    public R userInfo() {
        return authservice.userInfo();
    }
}

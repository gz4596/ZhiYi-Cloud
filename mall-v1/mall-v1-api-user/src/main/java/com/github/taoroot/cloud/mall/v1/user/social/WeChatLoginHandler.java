package com.github.taoroot.cloud.mall.v1.user.social;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.social.AbstractSocialLoginHandler;
import com.github.taoroot.cloud.mall.v1.common.entity.MallUser;
import com.github.taoroot.cloud.mall.v1.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Component;

@Log4j2
@Component("WX")
@AllArgsConstructor
public class WeChatLoginHandler extends AbstractSocialLoginHandler {
    private final WxMpService wxMpService;
    private final UserMapper userMapper;
    private final static ThreadLocal<WxMpOAuth2AccessToken> tokenThreadLocal = new ThreadLocal<>();

    @Override
    public String identify(String code) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            tokenThreadLocal.set(wxMpOAuth2AccessToken);
        } catch (WxErrorException e) {
            log.error("WeChatLoginHandler Error", e);
            return null;
        }
        return wxMpOAuth2AccessToken.getOpenId();
    }

    @Override
    public AuthUserInfo info(String openId) {
        MallUser user = userMapper.selectOne(Wrappers
                .<MallUser>lambdaQuery()
                .eq(MallUser::getOpenid, openId));

        if (user == null) {
            user = new MallUser();
            user.setOpenid(openId);
            try {
                // 新用户完善用户信息. 如果用户拒绝授权,平台将看不到用户头像信息
                WxMpOAuth2AccessToken wxMpOAuth2AccessToken = tokenThreadLocal.get();
                WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
                user.setNickname(wxMpUser.getNickname());
                user.setCity(wxMpUser.getCity());
                user.setSex(wxMpUser.getSex());
                user.setHeadImgUrl(wxMpUser.getHeadImgUrl());
            } catch (WxErrorException e) {
                e.printStackTrace();
            } finally {
                tokenThreadLocal.remove();
            }
            userMapper.insert(user);
        }

        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setUsername(String.valueOf(user.getId()));
        authUserInfo.setNickname(user.getNickname());
        authUserInfo.setAuthorities(new String[]{"ROLE_USER"});
        return authUserInfo;
    }
}

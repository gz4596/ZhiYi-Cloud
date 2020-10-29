package com.github.taoroot.cloud.mall.v1.admin.service.social;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.common.security.social.AbstractSocialUserHandler;
import com.github.taoroot.cloud.common.security.social.SocialType;
import com.github.taoroot.cloud.common.security.social.SocialUser;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserMapper;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserSocialMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.UserSocialService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminSocialDetails;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUserSocial;
import com.github.taoroot.cloud.mall.v1.common.mapper.SocialDetailsMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Component(SocialType.WX_OPEN)
@AllArgsConstructor
public class WxOpenUserHandler extends AbstractSocialUserHandler {
    private final UserMapper userMapper;
    private final SocialDetailsMapper socialDetailsMapper;
    private final UserSocialMapper userSocialMapper;
    private final UserSocialService userSocialService;

    @Override
    public Map<String, Object> getToken(String code, String redirectUri) {
        AdminSocialDetails socialDetails = socialDetailsMapper
                .selectOne(Wrappers.<AdminSocialDetails>lambdaQuery().eq(AdminSocialDetails::getType, SocialType.WX_OPEN));
        String uri = String.format(SocialType.WX_OAUTH2_ACCESS_TOKEN_URL,
                socialDetails.getAppId(),
                socialDetails.getAppSecret(), code);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        Map<String, Object> body = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .getBody();

        log.debug("{}:{} --> {}", SocialType.WX_OPEN, code, body);

        return body;
    }

    @Override
    public SocialUser loadSocialUser(Map<String, Object> body) {
        String openid = (String) body.get("openid");
        SocialUser socialUser = new SocialUser();
        socialUser.setUsername(openid);
        return socialUser;
    }

    @Override
    public AuthUserInfo loadAuthUserInfo(SocialUser socialUser) {
        AdminUserSocial userSocial = userSocialMapper.selectOne(Wrappers.<AdminUserSocial>lambdaQuery()
                .eq(AdminUserSocial::getSocialType, SocialType.WX_OPEN)
                .eq(AdminUserSocial::getSocialId, socialUser.getName()));

        if (userSocial == null) {
            return null;
        }

        AdminUser user = userMapper.selectById(userSocial.getId());

        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setUsername(String.valueOf(user.getId()));
        authUserInfo.setNickname(user.getNickname());
        authUserInfo.setAuthorities(new String[]{"ROLE_USER"});
        return authUserInfo;
    }

    @Override
    public String bindAuthUserInfo(SocialUser socialUser) {
        // 检查当前社交账号有无已被绑定
        // 检查当前当前用户有无当前类型的社交账号绑定
        AdminUserSocial userSocial = userSocialMapper.selectOne(Wrappers.<AdminUserSocial>lambdaQuery()
                .eq(AdminUserSocial::getSocialType, SocialType.WX_OPEN)
                .eq(AdminUserSocial::getSocialId, socialUser.getName()));

        Assert.isNull(userSocial, "该社交账号已有绑定,请先解绑");

        Integer userId = SecurityUtils.userId();
        userSocial = userSocialMapper.selectOne(Wrappers.<AdminUserSocial>lambdaQuery()
                .eq(AdminUserSocial::getSocialType, SocialType.WX_OPEN)
                .eq(AdminUserSocial::getAdminUserId, userId));

        Assert.isNull(userSocial, "当前账号已有绑定社交账号,请先解绑当前社交账号");

        userSocial = new AdminUserSocial();
        userSocial.setAdminUserId(userId);
        userSocial.setSocialAvatar(socialUser.getAvatar());
        userSocial.setSocialId(socialUser.getName());
        userSocial.setSocialNickname(socialUser.getNickname());

        Assert.isTrue(userSocialService.save(userSocial), "未知原因,绑定失败");
        return "绑定成功";
    }

    public static class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public WxMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            mediaTypes.add(MediaType.TEXT_HTML);  // 解决微信问题:  放回格式是 text/plain 的问题
            setSupportedMediaTypes(mediaTypes);
        }
    }
}

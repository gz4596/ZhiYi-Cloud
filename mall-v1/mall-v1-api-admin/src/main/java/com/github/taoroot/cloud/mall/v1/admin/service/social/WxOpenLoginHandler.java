package com.github.taoroot.cloud.mall.v1.admin.service.social;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.social.AbstractSocialLoginHandler;
import com.github.taoroot.cloud.common.security.social.SocialType;
import com.github.taoroot.cloud.mall.v1.admin.mapper.SocialDetailsMapper;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserMapper;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserSocialMapper;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminSocialDetails;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUserSocial;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Component(SocialType.WX_OPEN)
@AllArgsConstructor
public class WxOpenLoginHandler extends AbstractSocialLoginHandler {
    private final UserMapper userMapper;
    private final SocialDetailsMapper socialDetailsMapper;
    private final UserSocialMapper userSocialMapper;

    @Override
    public String identify(String code, String redirectUri) {
        AdminSocialDetails socialDetails = socialDetailsMapper
                .selectOne(Wrappers.<AdminSocialDetails>lambdaQuery().eq(AdminSocialDetails::getType, SocialType.WX_OPEN));
        String uri = String.format(SocialType.WX_OPEN_ACCESS_TOKEN_URL,
                socialDetails.getAppId(),
                socialDetails.getAppSecret(), code);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        Map<String, Object> body = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .getBody();

        log.debug("{}:{} --> {}", SocialType.WX_OPEN, code, body);

        if (body == null) {
            return "-1";
        }

        return (String) body.get("openid");
    }

    @Override
    public AuthUserInfo info(String openId) {
        AdminUserSocial userSocial = userSocialMapper.selectOne(Wrappers.<AdminUserSocial>lambdaQuery()
                .eq(AdminUserSocial::getSocialType, SocialType.WX_OPEN)
                .eq(AdminUserSocial::getSocialId, openId));

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

    public static class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public WxMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            mediaTypes.add(MediaType.TEXT_HTML);  // 解决微信问题:  放回格式是 text/plain 的问题
            setSupportedMediaTypes(mediaTypes);
        }
    }
}

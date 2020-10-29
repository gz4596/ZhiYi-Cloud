package com.github.taoroot.cloud.mall.v1.user.service.social;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.social.AbstractSocialUserHandler;
import com.github.taoroot.cloud.common.security.social.SocialType;
import com.github.taoroot.cloud.common.security.social.SocialUser;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminSocialDetails;
import com.github.taoroot.cloud.mall.v1.common.entity.MallUser;
import com.github.taoroot.cloud.mall.v1.common.mapper.SocialDetailsMapper;
import com.github.taoroot.cloud.mall.v1.user.mapper.UserMapper;
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
@Component(SocialType.WX_MP)
@AllArgsConstructor
public class WxMpUserHandler extends AbstractSocialUserHandler {
    private final UserMapper userMapper;
    private final SocialDetailsMapper socialDetailsMapper;


    @Override
    public Map<String, Object> getToken(String code, String redirectUri) {
        AdminSocialDetails socialDetails = socialDetailsMapper
                .selectOne(Wrappers.<AdminSocialDetails>lambdaQuery().eq(AdminSocialDetails::getType, SocialType.WX_MP));
        String uri = String.format(SocialType.WX_OAUTH2_ACCESS_TOKEN_URL, socialDetails.getAppId(),
                socialDetails.getAppSecret(), code);

        Map<String, Object> body = getStringObjectMap(uri);

        log.debug("{}:{} --> {}", SocialType.WX_OPEN, code, body);
        return body;
    }

    @Override
    public SocialUser loadSocialUser(Map<String, Object> token) {
        String openid = (String) token.get("openid");
        String accessToken = (String) token.get("accessToken");
        String url = String.format(SocialType.WX_OAUTH2_USERINFO_URL, accessToken, openid, "zh_CN");
        Map<String, Object> body = getStringObjectMap(url);
        assert body != null;
        SocialUser socialUser = new SocialUser();
        socialUser.setUsername(openid);
        socialUser.setAvatar((String) body.get("headImgUrl"));
        return socialUser;
    }


    @Override
    public AuthUserInfo loadAuthUserInfo(SocialUser socialUser) {
        MallUser user = userMapper.selectOne(Wrappers
                .<MallUser>lambdaQuery()
                .eq(MallUser::getOpenid, socialUser.getName()));

        if (user == null) {
            user = new MallUser();
            user.setOpenid(socialUser.getName());
            user.setNickname(socialUser.getNickname());
            user.setHeadImgUrl(socialUser.getAvatar());
            userMapper.insert(user);
        }

        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setUsername(String.valueOf(user.getId()));
        authUserInfo.setNickname(user.getNickname());
        authUserInfo.setAuthorities(new String[]{"ROLE_USER"});
        return authUserInfo;
    }

    @Override
    public String bindAuthUserInfo(SocialUser socialUser) {
        return null;
    }

    public static class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public WxMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            mediaTypes.add(MediaType.TEXT_HTML);  // 解决微信问题:  放回格式是 text/plain 的问题
            setSupportedMediaTypes(mediaTypes);
        }
    }

    private Map<String, Object> getStringObjectMap(String url) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .getBody();
    }
}

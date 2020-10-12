package com.github.taoroot.cloud.mall.v1.admin.service.social;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.common.security.social.AbstractSocialLoginHandler;
import com.github.taoroot.cloud.common.security.social.SocialType;
import com.github.taoroot.cloud.mall.v1.admin.mapper.SocialDetailsMapper;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserMapper;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminSocialDetails;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Log4j2
@Component(SocialType.ZHIYI)
@AllArgsConstructor
public class ZhiYiLoginHandler extends AbstractSocialLoginHandler {
    private final UserMapper userMapper;
    private final SocialDetailsMapper socialDetailsMapper;
    private final RestTemplate restTemplate;

    @Override
    public Boolean check(String code, String redirectUri) {
        return StringUtils.isNotBlank(code) && StringUtils.isNotBlank(redirectUri) ;
    }

    @Override
    public String identify(String code, String redirectUri) {
        AdminSocialDetails socialDetails = socialDetailsMapper
                .selectOne(Wrappers.<AdminSocialDetails>lambdaQuery().eq(AdminSocialDetails::getType, SocialType.ZHIYI));
        String uri = String.format(SocialType.ZHIYI_ACCESS_TOKEN_URL,
                socialDetails.getAppId(),
                socialDetails.getAppSecret(),
                code,
                redirectUri);
        Map<String, Object> body = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(SecurityUtils.httpHeaders()),
                new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .getBody();
        log.debug("{}:{} --> {}", SocialType.ZHIYI, code, body);

        if (body == null) {
            return "-1";
        }

        return (String) body.get(SecurityConstants.USER_ID);
    }

    @Override
    public AuthUserInfo info(String openId) {
        AdminUser user = userMapper.selectById(openId);
        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setUsername(String.valueOf(user.getId()));
        authUserInfo.setNickname(user.getNickname());
        authUserInfo.setAuthorities(new String[]{"ROLE_USER"});
        return authUserInfo;
    }

}

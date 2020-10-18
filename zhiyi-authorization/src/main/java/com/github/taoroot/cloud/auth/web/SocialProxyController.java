package com.github.taoroot.cloud.auth.web;

import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.common.security.annotation.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class SocialProxyController {

    /**
     * 微信开放平台只能设置一个域名,所以这里自己做了一个跳转
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=authorize&connect_redirect=1#wechat_redirect
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfc4b369dbf9c4d95&redirect_uri=http%3A%2F%2Flocalhost%3A9528%2F%23%2Fauth-redirect&response_type=code&scope=snsapi_userinfo&state=authorize&connect_redirect=1#wechat_redirect
     */
    @PermitAll
    @GetMapping(value = "/social/proxy/authorize")
    public String requestWxCode(HttpSession session,
                                String redirect_uri,
                                @RequestParam(SecurityConstants.OAUTH2_PROXY_ORIGIN_PARAM) String target) {
        HttpServletRequest request = SecurityUtils.request();

        if (request.getQueryString() != null) {
            target = target + "?" + request.getQueryString();
        }

        String requestURL = request.getRequestURL().toString()
                .replaceFirst(request.getServletPath(), "/code_callback");

        UriComponents requestUri = UriComponentsBuilder
                .fromUriString(target) // 指向真正授权的地址
                .replaceQueryParam(SecurityConstants.OAUTH2_PROXY_ORIGIN_PARAM, (Object) null)
                .replaceQueryParam("redirect_uri", requestURL) // 替换调原来的 redirect_uri
                .build();
        session.setAttribute("social_code_redirect_uri", redirect_uri); // 保存原来的 redirect_uri
        return "redirect:" + requestUri;
    }

    @PermitAll
    @GetMapping(value = "/code_callback")
    public String callbackWxCode(String code, String state, HttpSession session) {
        String wxRedirectUri = (String) session.getAttribute("social_code_redirect_uri");
        UriComponents requestUri = UriComponentsBuilder
                .fromUriString(wxRedirectUri)
                .queryParam("code", code)
                .queryParam("state", state)
                .build();
        return "redirect:" + requestUri.toUriString();
    }
}

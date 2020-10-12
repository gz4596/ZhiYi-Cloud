package com.github.taoroot.cloud.auth.web;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.IdUtil;
import com.github.taoroot.cloud.auth.social.SocialDetailsService;
import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import com.github.taoroot.cloud.common.core.utils.CaptchaCacheService;
import com.github.taoroot.cloud.common.core.vo.AuthSocialInfo;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.common.security.annotation.NoAuth;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */
@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final CaptchaCacheService captchaCacheService;

    private final SocialDetailsService socialDetailsService;

    /**
     * 微信开放平台只能设置一个域名,所以这里自己做了一个跳转
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=authorize&connect_redirect=1#wechat_redirect
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfc4b369dbf9c4d95&redirect_uri=http%3A%2F%2Flocalhost%3A9528%2F%23%2Fauth-redirect&response_type=code&scope=snsapi_userinfo&state=authorize&connect_redirect=1#wechat_redirect
     */
    @NoAuth
    @GetMapping(value = "/social/proxy/authorize")
    public String requestWxCode(HttpSession session,
                                String redirect_uri,
                                @RequestParam(SecurityConstants.OAUTH2_PROXY_PARAM) String target) {
        HttpServletRequest request = SecurityUtils.request();

        if (request.getQueryString() != null) {
            target = target + "?" + request.getQueryString();
        }

        String requestURL = request.getRequestURL().toString()
                .replaceFirst(request.getServletPath(), "/code_callback");

        UriComponents requestUri = UriComponentsBuilder
                .fromUriString(target) // 指向真正授权的地址
                .replaceQueryParam(SecurityConstants.OAUTH2_PROXY_PARAM, (Object) null) // 删除target参数
                .replaceQueryParam("redirect_uri", requestURL) // 替换调原来的 redirect_uri
                .build();
        session.setAttribute("social_code_redirect_uri", redirect_uri); // 保存原来的 redirect_uri
        return "redirect:" + requestUri;
    }

    @NoAuth
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

    @SneakyThrows
    @GetMapping(value = "/login")
    public String login(Model model) {
        HttpServletRequest request = SecurityUtils.request();
        String encode = URLEncoder.encode(request.getRequestURL().toString(), "UTF-8");
        try {
            List<AuthSocialInfo> socials = socialDetailsService.getSocials(encode);
            model.addAttribute("socials", socials);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        model.addAttribute("imageCode", IdUtil.fastSimpleUUID());
        return "login";
    }

    @GetMapping(value = "/v2/api-docs")
    @ResponseBody
    @SneakyThrows
    public String apiDocs() {
        String prefix = SecurityUtils.request().getHeader("X-Forwarded-Prefix");
        String host = SecurityUtils.request().getHeader("X-Forwarded-Host");
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource("classpath:api-docs.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        StringBuilder data = new StringBuilder();
        String temp;
        while ((temp = reader.readLine()) != null) {
            data.append(temp);
        }
        String json = data.toString();
        if (!StringUtils.isEmpty(prefix)) {
            return json.replace("\"basePath\": \"/\"", "\"basePath\": \"" + prefix + "/\"");
        }
        if (!StringUtils.isEmpty(host)) {
            return json.replace("\"host\": \"localhost:6628\"", "\"host\": \"" + host + "/\"");
        }
        return json;
    }

    @NoAuth
    @GetMapping(value = "/captcha/image")
    public void getImage(HttpServletResponse response, @RequestParam("key") String key) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("cache-Control", "no-cache, must-revalidate");
        response.addHeader("cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        try (ServletOutputStream out = response.getOutputStream()) {
            RandomGenerator randomGenerator = new RandomGenerator("023456789", 4);
            LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100);
            captcha.setGenerator(randomGenerator);
            captcha.createCode();
            captcha.write(out);
            out.flush();
            captchaCacheService.set(key, captcha.getCode(), 120);
        }
    }
}

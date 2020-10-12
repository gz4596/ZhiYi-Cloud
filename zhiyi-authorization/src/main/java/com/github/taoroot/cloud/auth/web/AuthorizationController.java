package com.github.taoroot.cloud.auth.web;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.IdUtil;
import com.github.taoroot.cloud.auth.social.SocialDetailsService;
import com.github.taoroot.cloud.common.core.utils.CaptchaCacheService;
import com.github.taoroot.cloud.common.core.vo.AuthSocialInfo;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.common.security.annotation.NoAuth;
import com.github.taoroot.cloud.common.security.social.SocialType;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final CaptchaCacheService captchaCacheService;

    private final SocialDetailsService socialDetailsService;

    @NoAuth
    @SneakyThrows
    @GetMapping(value = "/auth-redirect")
    public String authRedirect(Model model) {
        model.addAttribute("redirect_uri", SecurityUtils.request().getRequestURL().toString());
        return "auth-redirect";
    }

    @SneakyThrows
    @GetMapping(value = "/login")
    public String login(Model model) {
        try {
            HttpServletRequest request = SecurityUtils.request();
            String requestURL = request.getRequestURL().toString()
                    .replaceFirst(request.getServletPath(), "/auth-redirect");
            List<AuthSocialInfo> socials = socialDetailsService.getSocials(requestURL).stream()
                    .filter(item -> !SocialType.ZHIYI.equals(item.getType()))
                    .collect(Collectors.toList());
            model.addAttribute("socials", socials);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        model.addAttribute("imageCode", IdUtil.fastSimpleUUID());
        return "login";
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
}

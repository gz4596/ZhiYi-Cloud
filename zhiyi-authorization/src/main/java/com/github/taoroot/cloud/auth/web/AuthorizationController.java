package com.github.taoroot.cloud.auth.web;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.github.taoroot.cloud.common.core.utils.CaptchaCacheService;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */
@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final CaptchaCacheService captchaCacheService;

    @GetMapping(value = "/login")
    public String login(Model model) {
        // 已经登录不能再进入登录界面
        return "login";
    }

    @GetMapping(value = "/v2/api-docs")
    @ResponseBody
    @SneakyThrows
    public String apiDocs(HttpServletRequest httpServletRequest) {
        String prefix = httpServletRequest.getHeader("X-Forwarded-Prefix");
        String host = httpServletRequest.getHeader("X-Forwarded-Host");
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

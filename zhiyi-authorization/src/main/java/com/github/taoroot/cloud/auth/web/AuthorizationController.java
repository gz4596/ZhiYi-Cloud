package com.github.taoroot.cloud.auth.web;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */
@Controller
@AllArgsConstructor
public class AuthorizationController {

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
        return json;
    }
}

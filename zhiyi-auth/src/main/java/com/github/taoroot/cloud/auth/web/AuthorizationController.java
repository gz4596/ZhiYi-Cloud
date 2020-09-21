package com.github.taoroot.cloud.auth.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}

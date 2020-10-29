package com.github.taoroot.cloud.mall.v1.user.service.social;

import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeChatLoginHandlerTest {

    @Autowired
    private WxMpService wxMpService;

    /**
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfc4b369dbf9c4d95&redirect_uri=http%3A%2F%2F127.0.0.1&response_type=code&scope=snsapi_userinfo&state=authorize&connect_redirect=1#wechat_redirect
     */

    @Test
    public void info() {
        String abc = wxMpService.oauth2buildAuthorizationUrl(
                "http://127.0.0.1", "snsapi_userinfo",
                "authorize"

        );
        System.out.println(abc);
    }
}

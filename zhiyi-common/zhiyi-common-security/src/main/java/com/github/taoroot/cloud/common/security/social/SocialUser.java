package com.github.taoroot.cloud.common.security.social;

import lombok.Data;
import org.springframework.security.core.AuthenticatedPrincipal;

@Data
public class SocialUser implements AuthenticatedPrincipal {

    private String accessToken;

    private String username;

    private String nickname;

    private String avatar;

    @Override
    public String getName() {
        return username;
    }
}

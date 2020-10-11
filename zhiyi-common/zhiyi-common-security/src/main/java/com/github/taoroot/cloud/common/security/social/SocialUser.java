package com.github.taoroot.cloud.common.security.social;

import org.springframework.security.core.AuthenticatedPrincipal;

import java.util.Map;

public interface SocialUser extends AuthenticatedPrincipal {

    Map<String, Object> attrs();
}

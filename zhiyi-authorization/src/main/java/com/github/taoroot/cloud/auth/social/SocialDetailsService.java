package com.github.taoroot.cloud.auth.social;

import com.github.taoroot.cloud.common.core.vo.AuthSocialInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface SocialDetailsService {

    UserDetails loadUserBySocial(String type, String code) throws UsernameNotFoundException;

    List<AuthSocialInfo> getSocials(String redirectUrl);
}

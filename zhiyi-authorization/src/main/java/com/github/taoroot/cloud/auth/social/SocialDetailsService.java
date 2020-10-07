package com.github.taoroot.cloud.auth.social;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SocialDetailsService {

	UserDetails loadUserBySocial(String type, String code) throws UsernameNotFoundException;
}

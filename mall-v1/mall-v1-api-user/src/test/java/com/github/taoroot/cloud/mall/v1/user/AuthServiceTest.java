package com.github.taoroot.cloud.mall.v1.user;

import com.github.taoroot.cloud.common.security.AuthUser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
class AuthServiceTest {

    public void init() {
        AuthUser user = new AuthUser("1", AuthorityUtils.commaSeparatedStringToAuthorityList("USER"));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }
}

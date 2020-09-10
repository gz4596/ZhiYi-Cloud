package com.github.taoroot.cloud.auth.service;

import com.github.taoroot.cloud.auth.util.UserDetailsServiceProperties;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Component
@AllArgsConstructor
public class RemoteUserDetailService implements UserDetailsService {

    final UserDetailsServiceProperties userDetailsServiceProperties;
    final RestTemplate lbRestTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetailsServiceProperties.Info info = userDetailsServiceProperties.getRoute().get(clientId);

        if (info == null) {
            throw new UsernameNotFoundException("客户端不存在");
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("username", Collections.singletonList(username));
        params.put("client", Collections.singletonList(clientId));
        params.put("token", Collections.singletonList(info.getToken()));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(info.getPath());
        URI uri = builder.queryParams(params).build().encode().toUri();

        AuthUserInfo userInfo = lbRestTemplate
                .exchange(uri, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), AuthUserInfo.class)
                .getBody();

        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return new User(userInfo.getUserId(),
                userInfo.getPassword(),
                AuthorityUtils.createAuthorityList(userInfo.getAuthorities()));
    }
}
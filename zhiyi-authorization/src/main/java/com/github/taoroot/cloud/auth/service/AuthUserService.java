package com.github.taoroot.cloud.auth.service;

import com.github.taoroot.cloud.auth.social.SocialDetailsService;
import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.vo.AuthSocialInfo;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.AuthUser;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.common.security.tenant.TenantContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class AuthUserService implements UserDetailsService, SocialDetailsService {

    @Autowired
    private RestTemplate lbRestTemplate;
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientDetails clientDetails = getClientDetails();

        Map<String, Object> additionalInformation = clientDetails.getAdditionalInformation();
        String path = (String) additionalInformation.get(SecurityConstants.AUTH_USER_PATH);

        if (StringUtils.isEmpty(path)) {
            throw new UsernameNotFoundException(SecurityConstants.AUTH_USER_PATH + " 不存在");
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("username", Collections.singletonList(username));
        params.put("client", Collections.singletonList(clientDetails.getClientId()));

        return getUserDetails(path, params);
    }

    @Override
    public UserDetails loadUserBySocial(String type, String code) throws UsernameNotFoundException {
        ClientDetails clientDetails = getClientDetails();

        Map<String, Object> additionalInformation = clientDetails.getAdditionalInformation();
        String path = (String) additionalInformation.get(SecurityConstants.AUTH_SOCIAL_PATH);

        if (StringUtils.isEmpty(path)) {
            throw new UsernameNotFoundException(SecurityConstants.AUTH_SOCIAL_PATH + " 不存在");
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("type", Collections.singletonList(type));
        params.put("code", Collections.singletonList(code));
        params.put("client", Collections.singletonList(clientDetails.getClientId()));
        return getUserDetails(path, params);
    }

    @Override
    public List<AuthSocialInfo> getSocials(String redirectUrl) {
        ClientDetails clientDetails;
        try {
            RequestCache requestCache = new HttpSessionRequestCache();
            SavedRequest request = requestCache.getRequest(SecurityUtils.request(), null);
            String clientId = request.getParameterMap().get("client_id")[0];
            clientDetails = clientDetailsService.loadClientByClientId(clientId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("客户端不存在");
        }
        if (clientDetails == null) {
            throw new UsernameNotFoundException("客户端不存在");
        }
        Map<String, Object> additionalInformation = clientDetails.getAdditionalInformation();
        String path = (String) additionalInformation.get(SecurityConstants.AUTH_SOCIAL_LIST_PATH);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("redirectUri", Collections.singletonList(redirectUrl));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path);
        URI uri = builder.queryParams(params).build().encode().toUri();
        HttpHeaders httpHeaders = getHttpHeaders();
        List<AuthSocialInfo> socialInfos = new ArrayList<>();
        try {
            R<List<AuthSocialInfo>> r = lbRestTemplate.exchange(
                    uri, HttpMethod.GET, new HttpEntity<>(httpHeaders),
                    new ParameterizedTypeReference<R<List<AuthSocialInfo>>>() {
                    }).getBody();
            if (r != null && r.getCode() == 0) {
                socialInfos = r.getData();
            }
        } catch (Exception e) {
            // do nothing
        }
        return socialInfos;
    }


    private HttpHeaders getHttpHeaders() {
        HttpServletRequest request = SecurityUtils.request();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstants.FROM, SecurityConstants.FROM_IN);
        httpHeaders.add(SecurityConstants.TENANT_ID, String.valueOf(TenantContextHolder.get()));
        httpHeaders.add("X-Forwarded-For", request.getHeader("X-Forwarded-For"));
        httpHeaders.add("X-Forwarded-Host", request.getHeader("X-Forwarded-Host"));
        httpHeaders.add("X-Real-IP", request.getHeader("X-Real-IP"));
        return httpHeaders;
    }

    /**
     * 获取客户端信息
     *
     * @return
     */
    private ClientDetails getClientDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new UsernameNotFoundException("客户端不存在");
        }

        String clientId = authentication.getName();

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UsernameNotFoundException("客户端不存在");
        }
        return clientDetails;
    }

    /**
     * 获取用户信息
     *
     * @param path
     * @param params
     * @return
     */
    private UserDetails getUserDetails(String path, MultiValueMap<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path);
        URI uri = builder.queryParams(params).build().encode().toUri();
        HttpHeaders httpHeaders = getHttpHeaders();
        AuthUserInfo authUserInfo = null;
        try {
            R<AuthUserInfo> r = lbRestTemplate
                    .exchange(uri, HttpMethod.GET, new HttpEntity<>(httpHeaders),
                            new ParameterizedTypeReference<R<AuthUserInfo>>() {
                            })
                    .getBody();
            if (r != null && r.getCode() == 0) {
                authUserInfo = r.getData();
            }
        } catch (Exception e) {
            // do nothing
        }

        AuthUser authUser = SecurityUtils.getAuthUser(authUserInfo);
        if (authUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return authUser;
    }
}

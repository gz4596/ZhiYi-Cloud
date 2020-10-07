package com.github.taoroot.cloud.auth.service;

import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@AllArgsConstructor
@SuppressWarnings("deprecation")
public class JwtSignerService implements Signer {

    private final ClientDetailsService clientDetailsService;
    final Map<String, MacSigner> map = new ConcurrentHashMap<>();

    @Override
    public byte[] sign(byte[] bytes) {
        String clientId = SecurityContextHolder.getContext().getAuthentication().getName();
        String key = getSinger(clientId);

        return map.get(key).sign(bytes);
    }


    @Override
    public String algorithm() {
        String clientId = SecurityContextHolder.getContext().getAuthentication().getName();
        return map.get(getSinger(clientId)).algorithm();
    }


    private String getSinger(String clientId) {
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        Map<String, Object> additionalInformation = clientDetails.getAdditionalInformation();
        String secret = (String) additionalInformation.get(SecurityConstants.AUTH_JWT_SECRET);

        if (StringUtils.isEmpty(secret)) {
            throw new UsernameNotFoundException(SecurityConstants.AUTH_JWT_SECRET + "不存在");
        }

        String key = clientId + ":" + secret;

        if (!map.containsKey(key)) {
            synchronized (this) {
                if (!map.containsKey(key)) {
                    map.put(key, new MacSigner(secret));
                }
            }
        }
        return key;
    }
}

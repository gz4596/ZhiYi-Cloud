package com.github.taoroot.cloud.common.security.config;

import com.github.taoroot.cloud.common.security.filter.PreviewFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Log4j2
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // do nothing
        http.addFilterAfter(new PreviewFilter(), AbstractPreAuthenticatedProcessingFilter.class);
    }

}

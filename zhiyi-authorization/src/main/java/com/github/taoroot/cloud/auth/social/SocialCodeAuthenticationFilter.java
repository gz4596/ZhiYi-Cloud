package com.github.taoroot.cloud.auth.social;

import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Setter
public class SocialCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String typeParameter = "type";
    private String codeParameter = "code";
    private String redirectParameter = "redirect_uri";

    public SocialCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/social", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        String type = obtainUsername(request);
        String code = obtainPassword(request);
        String redirect = obtainRedirect(request);

        if (type == null) {
            type = "";
        }

        if (code == null) {
            code = "";
        }

        type = type.trim();
        code = code.trim();

        SocialCodeAuthenticationToken authRequest = new SocialCodeAuthenticationToken(type, code, redirect);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(codeParameter);
    }

    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(typeParameter);
    }

    protected String obtainRedirect(HttpServletRequest request) {
        return request.getParameter(redirectParameter);
    }


    protected void setDetails(HttpServletRequest request,
                              SocialCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}

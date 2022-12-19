package com.example.automobileservice.security;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;


public class RestPathAuthFilter extends AbstractRestAuthFilter {

    public RestPathAuthFilter(RequestMatcher requestAuthenticationRequestMatcher) {
        super(requestAuthenticationRequestMatcher);
    }

    @Override
    protected String getPassword(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("apiSecret");
    }

    @Override
    protected String getUsername(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("apiKey");
    }
}

package com.example.automobileservice.security;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;


public class RestHeaderAuthFilter extends AbstractRestAuthFilter {

    public RestHeaderAuthFilter(RequestMatcher requestAuthenticationRequestMatcher) {
        super(requestAuthenticationRequestMatcher);
    }

    protected String getPassword(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Api-Secret");
    }

    protected String getUsername(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Api-Key");
    }

}

package com.lambferret.playhttpground.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ServletFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper(response);

        var requestSession = httpServletRequest.getSession();
        var sessionId = requestSession.getId();
        SecurityContext sc = SecurityContextHolder.getContext();

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

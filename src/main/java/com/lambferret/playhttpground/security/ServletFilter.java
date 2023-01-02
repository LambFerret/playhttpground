package com.lambferret.playhttpground.security;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
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
        if (ObjectUtils.isEmpty(requestSession.getAttribute("number"))) {
            requestSession.setAttribute("number", 0);
        }


        filterChain.doFilter(request, response);
    }
}

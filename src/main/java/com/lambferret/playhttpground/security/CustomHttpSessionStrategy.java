package com.lambferret.playhttpground.security;

import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomHttpSessionStrategy extends HeaderHttpSessionIdResolver {

    private final String headerName;

    /**
     * The name of the header to obtain the session id from.
     *
     * @param headerName the name of the header to obtain the session id from.
     */
    public CustomHttpSessionStrategy(String headerName) {
        super(headerName);
        this.headerName = headerName;
    }

    /*
    중요한 파트; 세션과 쿠키를 엮는 부분;
     */
    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        System.out.println("IM in session strategy");
        if (ObjectUtils.isEmpty(request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"))) {
            expireSession(request, response);
            request.getSession().invalidate();
            System.out.println("is out?");
        } else {
            Cookie cookie = new Cookie("X-AUTH-TOKEN", sessionId);
            cookie.setPath("/");
            System.out.println("this is session ID" + sessionId);
            response.addCookie(cookie);
            response.setHeader(this.headerName, sessionId);
        }
    }
}

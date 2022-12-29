package com.lambferret.playhttpground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(
            @CookieValue(name = "lamb", defaultValue = "no") String cookieValue,
            HttpSession session,
            HttpServletRequest request, HttpServletResponse response
    ) {
        Cookie cookie;
        if (cookieValue.equals("ferret")) {
            cookie = new Cookie("lamb","ferret222");
        } else {
            cookie = new Cookie("lamb","ferret");
        }

        cookie.setPath("/");
        response.addCookie(cookie);

        var a = session.getId();


        System.out.println("0-0-0-0-0-0-0-");
        System.out.println(a);

        return "index";
    }
}

package com.lambferret.playhttpground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(@CookieValue("lamb") String lamb, HttpServletResponse response) {
        Cookie cookie;
        System.out.println("lamb?"+ lamb);
        if (lamb.equals("ferret")) {
            cookie = new Cookie("lamb","ferret222");
            System.out.println("lambhere");
        } else {
            cookie = new Cookie("lamb","ferret");
        }
        cookie.setPath("/");
        response.addCookie(cookie);

//        System.out.println(a)
//        ResponseEntity<Object> b =
//                ResponseEntity.badRequest().header(HttpHeaders.SET_COOKIE, a.toString()).build();

        return "index";
    }
}

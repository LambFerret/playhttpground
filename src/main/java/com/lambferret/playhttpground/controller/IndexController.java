package com.lambferret.playhttpground.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/session/destroy")
    public String clearSession(
            Authentication authentication, HttpServletRequest request, HttpServletResponse response
    ) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        request.getSession().invalidate();
        return "redirect:/";
    }

}

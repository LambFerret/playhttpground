package com.lambferret.playhttpground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(
            HttpServletRequest request, HttpServletResponse response
    ) {

        var session = request.getSession();


        return "index";
    }
}

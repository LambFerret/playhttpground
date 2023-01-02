package com.lambferret.playhttpground.controller;

import com.lambferret.playhttpground.exception.ApiErrorException;
import com.lambferret.playhttpground.exception.StatusCodes;
import com.lambferret.playhttpground.service.MovieService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    MovieService movieService;

    public IndexController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/session/destroy")
    public String clearSession(
            Authentication authentication, HttpServletRequest request, HttpServletResponse response
    ) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        var session = request.getSession();
        movieService.deleteSessionMovies(session.getId());
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/test")
    public String raiseExceptionTest() {
        throw new ApiErrorException(StatusCodes.F004);
    }

    @GetMapping("/random/post")
    public String postRandomTitle() {




        return "redirect:/";
    }

}

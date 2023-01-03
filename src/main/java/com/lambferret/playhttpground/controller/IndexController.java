package com.lambferret.playhttpground.controller;

import com.lambferret.playhttpground.document.dto.MovieDto;
import com.lambferret.playhttpground.exception.ApiErrorException;
import com.lambferret.playhttpground.exception.StatusCodes;
import com.lambferret.playhttpground.service.MovieService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @GetMapping("/session/destroy/all")
    public String clearAllSession(HttpServletRequest request) {
        movieService.deleteAllMoviesSession();
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/test")
    public String raiseExceptionTest() {
        throw new ApiErrorException(StatusCodes.F004);
    }

    @GetMapping("/random/post")
    public String postRandomTitle() {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MovieDto requestbody = new MovieDto();
        requestbody.setTitle("123123");
        requestbody.setRank("44");
        HttpEntity<List<MovieDto>> requestEntity = new HttpEntity<>(List.of(requestbody,requestbody,requestbody,requestbody,requestbody,requestbody), headers);
        rt.postForObject("http://localhost:10101/rest/movie", requestEntity, String.class);
        return "redirect:/";

    }

}

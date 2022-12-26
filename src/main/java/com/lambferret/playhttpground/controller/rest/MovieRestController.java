package com.lambferret.playhttpground.controller.rest;

import com.lambferret.playhttpground.document.Movie;
import com.lambferret.playhttpground.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/movie")
public class MovieRestController {
    MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Movie>> getAllMovies() {

        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }
}

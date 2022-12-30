package com.lambferret.playhttpground.controller.rest;

import com.lambferret.playhttpground.controller.response.PostResponse;
import com.lambferret.playhttpground.document.Movie;
import com.lambferret.playhttpground.document.dto.MoviePostDto;
import com.lambferret.playhttpground.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/movie")
public class MovieRestController {
    MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Movie>> getAllMovies(

    ) {
        List<Movie> movies = movieService.findUserMovies("test");
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PostResponse> makeNewMovies(
            @RequestBody List<MoviePostDto> newMoviesDto, HttpServletRequest request, HttpServletResponse response
    ) {
        var sessionId = request.getSession().getId();

        List<Movie> newMovies = new ArrayList<>();
        for (MoviePostDto movie : newMoviesDto) {
            var newMovie = Movie.builder()
                    .title(movie.getTitle())
                    .rank(movie.getRank())
                    .isFixed(false)
                    .ownerSession(sessionId)
                    .build();
            newMovies.add(newMovie);
        }

        movieService.saveAll(newMovies);
        var postResponse = new PostResponse();
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

}


/* 템플릿
@PostMapping()
    public ResponseEntity<PostResponse> makeNewMovies(
            @RequestBody MoviePostDto newMovie, HttpServletRequest request, HttpServletResponse response
    ) {
        var session = request.getSession();
        var postResponse = new PostResponse();
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


 */
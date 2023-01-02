package com.lambferret.playhttpground.controller.rest;

import com.lambferret.playhttpground.controller.response.PostResponse;
import com.lambferret.playhttpground.document.Movie;
import com.lambferret.playhttpground.document.dto.MovieDto;
import com.lambferret.playhttpground.exception.ApiErrorException;
import com.lambferret.playhttpground.exception.StatusCodes;
import com.lambferret.playhttpground.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/movie")
public class MovieRestController {
    MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public ResponseEntity<List<Movie>> getAllMovies(
            HttpServletRequest request, HttpServletResponse response
    ) {
        var session = request.getSession();

        var movies = movieService.findUserMovies("test");


        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Movie>> test(
            HttpServletRequest request, HttpServletResponse response
    ) {
        var session = request.getSession();
        if (Integer.parseInt(session.getAttribute("number").toString()) > 5) {
            throw new ApiErrorException(StatusCodes.F001);
        }
        throw new ApiErrorException(StatusCodes.F002);
//        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    private int sessionUsage(HttpSession request) {
        return Integer.parseInt(request.getAttribute("number").toString());
    }

    @PostMapping()
    public ResponseEntity<PostResponse> makeNewMovies(
            @RequestBody List<MovieDto> newMoviesDto, HttpServletRequest request, HttpServletResponse response
    ) {

        var session = request.getSession();
        var totalUsage = sessionUsage(session);

        if (totalUsage + newMoviesDto.size() > 5) throw new ApiErrorException(StatusCodes.F001);

        List<Movie> newMovies = new ArrayList<>();
        for (MovieDto movie : newMoviesDto) {
            var newMovie = Movie.builder()
                    .title(movie.getTitle())
                    .rank(movie.getRank())
                    .isFixed(false)
                    .ownerSession(session.getId())
                    .build();
            newMovies.add(newMovie);
        }

        session.setAttribute("number", totalUsage);
        movieService.saveAll(newMovies);
        var postResponse = new PostResponse();
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Movie> updateMovie(
            @RequestBody MovieDto newMovie, HttpServletRequest request, HttpServletResponse response
    ) {
        var session = request.getSession();

        var movie = movieService.findUserMovieWithTitle(session.getId(), newMovie.getTitle());
        movie.setRank(newMovie.getRank());
        movie.setTitle(newMovie.getTitle());
        movieService.saveAll(List.of(movie));
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> removeMovie(
            @RequestBody String title, HttpServletRequest request, HttpServletResponse response
    ) {
        var session = request.getSession();
        var sessionUsage = sessionUsage(session);
        movieService.deleteSessionMovieWithTitle(session.getId(), title);
        session.setAttribute("number", sessionUsage - 1);
        return new ResponseEntity<>("movie", HttpStatus.ACCEPTED);
    }

}


/* 템플릿
@PostMapping()
    public ResponseEntity<PostResponse> makeNewMovies(
            @RequestBody MovieDto newMovie, HttpServletRequest request, HttpServletResponse response
    ) {
        var session = request.getSession();
        var postResponse = new PostResponse();
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


 */
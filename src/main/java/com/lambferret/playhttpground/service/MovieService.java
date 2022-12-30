package com.lambferret.playhttpground.service;

import com.lambferret.playhttpground.document.Movie;
import com.lambferret.playhttpground.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findUserMovies(String sessionId) {
        return movieRepository.findAllbyOwnerSession(sessionId);
    }
    public void saveAll(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }
}

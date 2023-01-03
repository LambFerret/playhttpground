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
        return movieRepository.findAllAndOwnerSession(sessionId);
    }

    public void deleteSessionMovies(String sessionId) {
        movieRepository.deleteAllbyOwnerSession(sessionId);
    }

    public void deleteAllMoviesSession( ) {
        movieRepository.deleteAllSession();
    }

    public void deleteSessionMovieWithTitle(String sessionId, String title) {
        movieRepository.deletebyOwnerSessionAndTitle(sessionId, title);
    }

    public Movie findUserMovieWithTitle(String sessionId, String title) {
        return movieRepository.findAllbyOwnerSessionAndTitle(sessionId, title);
    }
    public void saveAll(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }
    public void save(Movie movie) {
        movieRepository.save(movie);
    }
}

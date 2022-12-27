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

    public void save() {
        Movie movie = new Movie();
        movie.setTitle("1122");
        movieRepository.save(movie);
    }
}

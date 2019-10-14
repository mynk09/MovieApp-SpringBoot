package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Service("dummy")
@Profile("test")

public class MovieDummyServiceImpl implements MovieService {

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    @Override
    public Movie getMovieById(int id) throws MovieNotFoundException {
        return null;
    }

    @Override
    public Movie deleteMovie(int id) throws MovieNotFoundException {

        return null;
    }

    @Override
    public Movie updateMovieComments(int id, String comments) throws MovieNotFoundException {
        return null;
    }

    @Override
    public List<Movie> getMoviesByName(String name) throws MovieNotFoundException {
        return null;
    }
}
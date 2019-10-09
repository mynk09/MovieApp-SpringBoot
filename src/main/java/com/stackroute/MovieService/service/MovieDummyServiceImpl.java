package com.stackroute.MovieService.service;

import com.stackroute.MovieService.domain.Movie;
import com.stackroute.MovieService.exception.MovieAlreadyExistsException;
import com.stackroute.MovieService.exception.MovieNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dummy")
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
    public void deleteMovie(int id) throws MovieNotFoundException {

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
package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;

import java.util.List;


public interface MovieService {
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;

    public List<Movie> getAllMovies();

    public Movie getMovieById(int id) throws MovieNotFoundException;

    public Movie deleteMovie(int id) throws MovieNotFoundException;

    public Movie updateMovieComments(int id, String comments) throws MovieNotFoundException;

    public List<Movie> getMoviesByName(String name) throws MovieNotFoundException;
}

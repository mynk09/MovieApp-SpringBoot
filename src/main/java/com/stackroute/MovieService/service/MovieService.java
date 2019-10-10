package com.stackroute.MovieService.service;

import com.stackroute.MovieService.domain.Movie;
import com.stackroute.MovieService.exception.MovieAlreadyExistsException;
import com.stackroute.MovieService.exception.MovieNotFoundException;

import java.util.List;


public interface MovieService {
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;

    public List<Movie> getAllMovies();

    public Movie getMovieById(int id) throws MovieNotFoundException;

    public Movie deleteMovie(int id) throws MovieNotFoundException;

    public Movie updateMovieComments(int id, String comments) throws MovieNotFoundException;

    public List<Movie> getMoviesByName(String name) throws MovieNotFoundException;
}

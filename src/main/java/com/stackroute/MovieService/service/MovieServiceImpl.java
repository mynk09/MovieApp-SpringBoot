package com.stackroute.MovieService.service;

import com.stackroute.MovieService.domain.Movie;
import com.stackroute.MovieService.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>(movieRepository.findAll());
        return movieList;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie retrievedMovie=movieRepository.findById(id).get();
        return retrievedMovie;
    }

    @Override
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateMovieComments(int id, String comments) {
        Movie updatedMovie = movieRepository.getOne(id);
        updatedMovie.setMovieComments(comments);
        return movieRepository.save(updatedMovie);
    }

    @Override
    public List<Movie> getMoviesByName(String name) {
        List<Movie> retrievedMovies = movieRepository.findByMovieNameIgnoreCase(name);
        return retrievedMovies;
    }
}

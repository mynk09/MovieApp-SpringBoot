package com.stackroute.MovieService.service;

import com.stackroute.MovieService.domain.Movie;
import com.stackroute.MovieService.exception.MovieNotFoundException;
import com.stackroute.MovieService.exception.MovieAlreadyExistsException;
import com.stackroute.MovieService.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException{
        if(movieRepository.existsById(movie.getMovieId())){
            throw new MovieAlreadyExistsException("Movie already exists");
        }
        Movie savedMovie = movieRepository.save(movie);
        if (savedMovie == null){
            throw new MovieAlreadyExistsException("Movie already exists");
        }
        return savedMovie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>(movieRepository.findAll());
        return movieList;
    }

    public Movie getMovieById(int id) throws MovieNotFoundException {
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException("Movie not found");
        }
        Movie retrievedMovie=movieRepository.findById(id).get();
        return retrievedMovie;
    }


    public void deleteMovie(int id) throws MovieNotFoundException {
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException("Movie does not exist");
        }
        movieRepository.deleteById(id);
    }

    public Movie updateMovieComments(int id, String comments) throws MovieNotFoundException{
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException("Movie not found");
        }
        Movie updatedMovie = movieRepository.getOne(id);
        updatedMovie.setMovieComments(comments);
        return movieRepository.save(updatedMovie);
    }

    public List<Movie> getMoviesByName(String name) throws MovieNotFoundException{
        List<Movie> retrievedMovies = movieRepository.findByMovieNameIgnoreCase(name);
        if(retrievedMovies.toString().equals("[]")){
            throw new MovieNotFoundException("No movie found with the given name");
        }
        return retrievedMovies;
    }

}

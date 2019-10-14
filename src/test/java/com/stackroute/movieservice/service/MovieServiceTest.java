package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    private Movie movie;
    private List<Movie> list = null;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movie = new Movie(101, "Before Sunrise", "100%","FG");
        list = new ArrayList<>();
        list.add(movie);
    }


    @Test
    public void saveMovie() throws Exception {
        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(movie,savedMovie);

        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveMovieFailure() throws Exception {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedMovie = movieService.saveMovie(movie);
        System.out.println("savedMovie: " + savedMovie);
    }

    @Test
    public void getAllMovies() throws Exception {
        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movieList = movieService.getAllMovies();
        Assert.assertEquals(list, movieList);
    }

    @Test
    public void getMovieById() throws Exception {
        when(movieRepository.findById(anyInt()).get()).thenReturn(movie);
        Movie retrievedMovie = movieService.getMovieById(movie.getMovieId());
        Assert.assertEquals(movie, retrievedMovie);
    }

    @Test(expected = MovieNotFoundException.class)
    public void getMovieByIdFailure() throws Exception {
        when(movieRepository.existsById(anyInt())).thenReturn(false);
        Movie retrievedMovie = movieService.getMovieById(movie.getMovieId());
        System.out.println("retrievedMovie: " + retrievedMovie);
    }

    @Test
    public void updateMovie() throws Exception {
        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie updatedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(movie, updatedMovie);
    }



    @Test
    public void getMoviesByName() throws Exception {
        when(movieRepository.findByMovieNameIgnoreCase(anyString())).thenReturn(list);
        List<Movie> movieList = movieService.getMoviesByName(movie.getMovieName());
        Assert.assertEquals(list, movieList);
    }



    @Test
    public void deleteMovie() throws Exception {
        when(movieRepository.findById(anyInt()).get()).thenReturn(movie);
        Movie deletedMovie = movieService.deleteMovie(movie.getMovieId());
        Assert.assertEquals(movie, deletedMovie);
    }

    @Test(expected = MovieNotFoundException.class)
    public void deleteMovieFailure() throws Exception {
        when(movieRepository.existsById(anyInt())).thenReturn(false);
        Movie deletedMovie = movieService.deleteMovie(movie.getMovieId());
        System.out.println("deletedMovie : " + deletedMovie);
    }

}
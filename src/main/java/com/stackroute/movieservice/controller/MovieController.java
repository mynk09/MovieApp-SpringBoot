package com.stackroute.movieservice.controller;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="api/v1")
public class MovieController {


    private MovieService movieService;
    private ResponseEntity responseEntity;

    @Autowired
    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }

    @PostMapping("movie")
    public ResponseEntity saveMovie(@RequestBody Movie movie)  {
        Movie savedMovie = null;
        try {
            savedMovie = movieService.saveMovie(movie);
        } catch (MovieAlreadyExistsException e) {
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        catch (Exception e) {
            responseEntity= new ResponseEntity<String>("Please try later",HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        responseEntity = new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("movie")
    public ResponseEntity getAllMovies() {
        ResponseEntity responseEntity;
        List<Movie> movieList = movieService.getAllMovies();
        responseEntity = new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("movie/{id}")
    public ResponseEntity getMovieById(@PathVariable int id) throws MovieNotFoundException {
        ResponseEntity responseEntity;
        Movie retrievedMovie = movieService.getMovieById(id);
        responseEntity = new ResponseEntity<Movie>(retrievedMovie, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("movie/{id}")
    public ResponseEntity deleteMovie(@PathVariable int id) throws MovieNotFoundException {
        ResponseEntity responseEntity;
        movieService.deleteMovie(id);
        responseEntity = new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("movie/{id}")
    public ResponseEntity updateComments(@PathVariable int id, @RequestBody String comments) throws MovieNotFoundException {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<Movie>(movieService.updateMovieComments(id, comments), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("movie/name/{name}")
    public ResponseEntity getMoviesByName(@PathVariable String name) throws MovieNotFoundException {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<List<Movie>>(movieService.getMoviesByName(name), HttpStatus.OK);
        return responseEntity;
    }

}

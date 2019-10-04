package com.stackroute.MovieService.controller;

import com.stackroute.MovieService.domain.Movie;
import com.stackroute.MovieService.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("movie")
    public ResponseEntity saveMovie(@RequestBody Movie movie) {
        ResponseEntity responseEntity;
        try {
            Movie savedMovie = movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("movie")
    public ResponseEntity getAllMovies() {
        ResponseEntity responseEntity;
        try {
            List<Movie> movieList = movieService.getAllMovies();
            responseEntity = new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("movie/{id}")
    public ResponseEntity getMovieById(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            Movie retrievedMovie = movieService.getMovieById(id);
            responseEntity = new ResponseEntity<Movie>(retrievedMovie, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("movie/{id}")
    public ResponseEntity deleteMovie(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            movieService.deleteMovie(id);
            responseEntity = new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PutMapping("movie/{id}")
    public ResponseEntity updateComments(@PathVariable int id, @RequestBody String comments) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Movie>(movieService.updateMovieComments(id, comments), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("movie/byName/{name}")
    public ResponseEntity getMoviesByName(@PathVariable String name){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Movie>>(movieService.getMoviesByName(name), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}

package com.stackroute.MovieService.StartUp;

import com.stackroute.MovieService.domain.Movie;
import com.stackroute.MovieService.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupDatabaseSeederApproach1 implements ApplicationListener<ContextRefreshedEvent> {

    private MovieRepository movieRepository;
    private Movie movie;

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setActorName("batman");
        movie.setMovieName("Joker");
        movie.setMovieComments("Unwatched");
        movieRepository.save(movie);
    }
}
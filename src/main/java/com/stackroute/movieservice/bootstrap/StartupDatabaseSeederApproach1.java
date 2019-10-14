package com.stackroute.movieservice.bootstrap;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:values.properties")
public class StartupDatabaseSeederApproach1 implements ApplicationListener<ContextRefreshedEvent> {

    private MovieRepository movieRepository;
    private Movie movie;

    @Value("${movie.id1}")
    private int id;

    @Autowired
    private Environment env;

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Movie movie1 = Movie.builder()
                .actorName("Srk")
                .movieId(1)
                .movieName("DDLJ")
                .movieComments("NICE")
                .build();

        Movie movie = new Movie();
        movie.setMovieId(id);
        movie.setMovieName(env.getProperty("movie.name1"));
        movie.setMovieComments(env.getProperty("movie.unwatch"));
        movie.setActorName(env.getProperty("movie.actor1"));
        movieRepository.save(movie);
        movieRepository.save(movie1);
    }
}
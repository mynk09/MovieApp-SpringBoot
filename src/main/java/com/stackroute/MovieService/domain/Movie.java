package com.stackroute.MovieService.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id
    private int movieId;
    private String actorName;
    private String movieComments;
    private String movieName;


    public Movie() {
    }

    public Movie(int movieId, String movieName, String movieComments, String actorName) {
        this.movieId = movieId;
        this.actorName=actorName;
        this.movieComments = movieComments;
        this.movieName = movieName;

    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getMovieComments() {
        return movieComments;
    }

    public void setMovieComments(String movieComments) {
        this.movieComments = movieComments;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", actorName='" + actorName + '\'' +
                ", movieComments='" + movieComments + '\'' +
                ", movieName='" + movieName + '\'' +
                '}';
    }
}


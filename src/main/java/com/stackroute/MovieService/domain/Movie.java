package com.stackroute.MovieService.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    private int movieId;
    private String actorName;
    private String movieComments;
    private String movieName;


    public Integer getmovieId() {
        return movieId;
    }

    public String setMovieComments(String comments) {
        return movieComments;
    }
}


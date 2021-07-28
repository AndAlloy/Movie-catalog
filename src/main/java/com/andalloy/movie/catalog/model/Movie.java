package com.andalloy.movie.catalog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private long id;

    private String imdbId;

    private String title;

    private String description;

    private double rating;

    private int ratingCount;

    private int year;

    public Movie(String imdbId,String title, String description, double rating, int ratingCount, int year) {
        this.imdbId = imdbId;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.year = year;
    }
}

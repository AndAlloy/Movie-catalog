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

    private String title;

    private String description;

    private double rating;

    private int ratingCount;

    private int year;

    public Movie(String title, String description, double rating, int ratingCount, int year) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.year = year;
    }
}

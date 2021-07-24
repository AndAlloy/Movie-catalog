package com.andalloy.movie.catalog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Year;
import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    private long id;

    private String title;

    private String description;

    private double rating;

    private int ratingCount;

    private int year;


}

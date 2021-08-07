package com.andalloy.movie.catalog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Map;

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

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
    private String description;

    private String shortDescription;

    private String image;

    private double rating;

    private int ratingCount;

    private int year;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "review")
    private Map<Long, String> review;

    public Movie(String imdbId,String title, String description, String shortDescription, double rating, int ratingCount, int year, String image) {
        this.imdbId = imdbId;
        this.title = title;
        this.description = description;
        this.shortDescription = shortDescription;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.year = year;
        this.image = image;
    }
}

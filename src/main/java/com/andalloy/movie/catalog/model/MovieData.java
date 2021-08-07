package com.andalloy.movie.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieData {
    private String id;
    private Title title;
    private Ratings ratings;
    private PlotSummary plotSummary;
    private PlotOutline plotOutline;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Title {
        private String title;
        private int year;
        private Image image;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Image {
            private String url;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Ratings {
        private double rating;
        private int ratingCount;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PlotSummary {
        private String text;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PlotOutline {
        private String text;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", title: " + getTitle().getTitle()
                + ", year: " + getTitle().getYear() + ", url: " + getTitle().getImage().getUrl()
                + "\nrating: " + getRatings().getRating() + ", rating count: " + getRatings().getRatingCount()
                + "\n text: " + getPlotSummary().getText();
    }
}

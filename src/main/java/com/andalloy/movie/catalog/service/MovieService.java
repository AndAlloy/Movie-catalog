package com.andalloy.movie.catalog.service;

import com.andalloy.movie.catalog.config.rest.RestConfig;
import com.andalloy.movie.catalog.model.MovieData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class MovieService {
    private final ObjectMapper mapper = new ObjectMapper();

    private final RestConfig restTemplate;

    public MovieService(RestConfig restTemplate) {
        this.restTemplate = restTemplate;
    }


    public MovieData getMovieData(String imdbId) {
        HttpClient httpClient = HttpClient.newBuilder()
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb8.p.rapidapi.com/title/get-overview-details?tconst=tt"+imdbId))
                .header("x-rapidapi-key", "146e3ec859mshe7fc99b2b761254p11bf6bjsncdc42113beb9")
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        MovieData movieData = null;

        try {
            JsonNode jsonNode = mapper.readTree(response.body());
            movieData = mapper.readValue(jsonNode.toString(), MovieData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return movieData;
    }
}

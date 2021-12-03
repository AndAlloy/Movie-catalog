package com.andalloy.movie.catalog.controller;

import com.andalloy.movie.catalog.model.Movie;
import com.andalloy.movie.catalog.model.MovieData;
import com.andalloy.movie.catalog.model.User;
import com.andalloy.movie.catalog.repository.MovieRepo;
import com.andalloy.movie.catalog.service.MovieService;
import com.andalloy.movie.catalog.service.RecommendService;
import com.andalloy.movie.catalog.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    private final MovieRepo movieRepo;
    private final MovieService movieService;
    private final UserService userService;
    private final RecommendService recommendService;


    public MovieController(MovieRepo movieRepo, MovieService movieService, UserService userService, RecommendService recommendService) {
        this.movieRepo = movieRepo;
        this.movieService = movieService;
        this.userService = userService;
        this.recommendService = recommendService;
    }

    @GetMapping("/catalog")
    public String catalog(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        List<Movie> movies = movieRepo.findAll();
        model.addAttribute("user", userService.buildUserDto(user));
        model.addAttribute("movies", movies);
        return "catalog";
    }

    @PostMapping("/catalog")
    public String addMovie(
            @RequestParam Map<String, String> form
    ) {
        String imdbId = form.get("imdbId");

        MovieData movieData = movieService.getMovieData(imdbId);
        movieRepo.save(movieService.convertToMovie(movieData));

        return "redirect:/catalog";
    }

    @GetMapping("/recommended")
    public String recommended(
            @AuthenticationPrincipal User user,
            Model model
    ) {

        model.addAttribute("recm", recommendService.getRecommended(user));
        model.addAttribute("user", userService.buildUserDto(user));
        return "recommendations";
    }

    @GetMapping("/item/{id}")
    public String showMoviePage(
            @PathVariable("id") long id,
            Model model,
            @AuthenticationPrincipal User user
    ) {
        Movie movie = movieService.geMovieFromDb(id);

        String userReview = movie.getTemporaryReview().get(user.getId());
        String approved = "false";
        if (movie.getReview().containsKey(user.getId())) {
            userReview = movie.getReview().get(user.getId());
            approved = "true";
        }
        movie.getReview().remove(user.getId());
        boolean isFavorite = user.getFavouriteList().contains(movie.getId());

        model.addAttribute("user", userService.buildUserDto(user));
        model.addAttribute("userReview", userReview);
        model.addAttribute("movie", movie);
        model.addAttribute("approved", approved);
        model.addAttribute("isFavourite", isFavorite);

        return "item";
    }

    @PostMapping("/item/{id}")
    public String addTempComment(
            @PathVariable("id") long id,
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form
    ) {
        String comment = form.get("comment");
        User foundUser = userService.getUserFromDb(user);
        Movie movie = movieService.geMovieFromDb(id);
        movie.getTemporaryReview().put(foundUser.getId(), comment);

        movieRepo.save(movie);

        return "redirect:/item/" + id;
    }

    @GetMapping("/delete/item-{itemId}/{userId}")
    public String deleteComment(
            @PathVariable("userId") long userId,
            @PathVariable("itemId") long itemId
    ) {
        Movie movie = movieService.geMovieFromDb(itemId);
        movie.getTemporaryReview().remove(userId);
        movie.getReview().remove(userId);

        movieRepo.save(movie);

        return "redirect:/item/" + itemId;
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(
            @PathVariable("id") long id
    ) {
        movieRepo.deleteById(id);

        return "redirect:/catalog";
    }
}

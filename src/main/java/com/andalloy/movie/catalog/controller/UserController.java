package com.andalloy.movie.catalog.controller;

import com.andalloy.movie.catalog.model.Movie;
import com.andalloy.movie.catalog.model.User;
import com.andalloy.movie.catalog.repository.MovieRepo;
import com.andalloy.movie.catalog.repository.UserRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserRepo userRepo;
    private final MovieRepo movieRepo;

    public UserController(UserRepo userRepo, MovieRepo movieRepo) {
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
    }

    @GetMapping("/user/{id}")
    public String getInfo(@PathVariable("id") long userId,
                          Model model) {
        User user = userRepo.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        model.addAttribute("user", user);

        return "user";
    }

    @GetMapping("/my-account")
    public String viewHome(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        User foundUser = userRepo.findByName(user.getUsername()).orElseThrow(NoSuchElementException::new);
        List<Long> favouriteList = foundUser.getFavouriteList();
        List<Movie> movies = movieRepo.findAll().stream()
                .filter(movie -> favouriteList.contains(movie.getId()))
                .collect(Collectors.toList());

        model.addAttribute("user", user);
        model.addAttribute("movies", movies);
        return "user";
    }

    @GetMapping("/item/add-favourite/{id}")
    public String addToFavouriteList(
            @PathVariable("id") long id,
            @AuthenticationPrincipal User user
    ) {
        User foundUser = userRepo.findByName(user.getUsername()).orElseThrow(NoSuchElementException::new);
        Movie movie = movieRepo.findById(id).orElseThrow(NoSuchElementException::new);
        foundUser.getFavouriteList().add(movie.getId());
        userRepo.save(foundUser);

        return "redirect:/catalog";
    }

    @GetMapping("/item/delete-favourite/{id}")
    public String deleteFromFavouriteList(
            @PathVariable("id") long id,
            @AuthenticationPrincipal User user
    ) {
        User foundUser = userRepo.findByName(user.getUsername()).orElseThrow(NoSuchElementException::new);
        Movie movie = movieRepo.findById(id).orElseThrow(NoSuchElementException::new);
        foundUser.getFavouriteList().remove(movie.getId());
        userRepo.save(foundUser);

        return "redirect:/my-account";
    }
}

package com.andalloy.movie.catalog.controller;

import com.andalloy.movie.catalog.model.Movie;
import com.andalloy.movie.catalog.model.User;
import com.andalloy.movie.catalog.repository.MovieRepo;
import com.andalloy.movie.catalog.repository.UserRepo;
import com.andalloy.movie.catalog.service.MovieService;
import com.andalloy.movie.catalog.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAnyAuthority('MODERATOR')")
public class ModeratorController {

    private final MovieRepo movieRepo;
    private final MovieService movieService;
    private final UserService userService;
    private final UserRepo userRepo;


    public ModeratorController(MovieRepo movieRepo, MovieService movieService, UserService userService, UserRepo userRepo) {
        this.movieRepo = movieRepo;
        this.movieService = movieService;
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping("/moderate")
    public String showTempComments(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        List<Movie> movieList = movieRepo.findAll()
                .stream()
                .filter(movie -> !movie.getTemporaryReview().isEmpty())
                .collect(Collectors.toList());

        model.addAttribute("movieList", movieList);
        model.addAttribute("user", userService.buildUserDto(user));

        return "moder-control";
    }

    @GetMapping("/approve/item-{itemId}/{userId}")
    public String approveComment(
            @PathVariable("userId") long userId,
            @PathVariable("itemId") long itemId
    ) {
        Movie movie = movieService.geMovieFromDb(itemId);
        String comment = movie.getTemporaryReview().get(userId);

        movie.getReview().put(userId, comment);
        movie.getTemporaryReview().remove(userId);

        movieRepo.save(movie);

        return "redirect:/moderate";
    }

    @GetMapping("/block/item-{itemId}/{userId}")
    public String blockUser(
            @PathVariable("userId") long userId,
            @PathVariable("itemId") long itemId
    ) {
        Movie movie = movieService.geMovieFromDb(itemId);
        User foundUser = userService.getUserById(userId);

        foundUser.setNonBlocked(false);
        userRepo.save(foundUser);
        movie.getTemporaryReview().remove(userId);
        movieRepo.save(movie);

        return "redirect:/moderate";
    }

    @GetMapping("/restrict/item-{itemId}/{userId}")
    public String restrictUser(
            @PathVariable("userId") long userId,
            @PathVariable("itemId") long itemId
    ) {
        Movie movie = movieService.geMovieFromDb(itemId);
        User foundUser = userService.getUserById(userId);

        foundUser.setNonRestricted(false);
        userRepo.save(foundUser);
        movie.getTemporaryReview().remove(userId);
        movieRepo.save(movie);

        return "redirect:/moderate";
    }
}

package com.andalloy.movie.catalog.controller;

import com.andalloy.movie.catalog.model.Movie;
import com.andalloy.movie.catalog.repository.MovieRepo;
import com.andalloy.movie.catalog.repository.UserRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/catalog")
public class MovieController {

    private final MovieRepo movieRepo;

    public MovieController(MovieRepo movieRepo, UserRepo userRepo) {
        this.movieRepo = movieRepo;
    }

    @GetMapping
    public String catalog(@AuthenticationPrincipal UserDetails user, Model model) {
        List<Movie> movies = movieRepo.findAll();
        model.addAttribute("user", user);
        model.addAttribute("movies", movies);
        return "catalog";
    }

    @PostMapping
    public String addMovie(@RequestParam Map<String, String> form) {
        Movie movie = new Movie(form.get("title"),
                form.get("description"),
                Double.parseDouble(form.get("rating")),
                Integer.parseInt(form.get("ratingCount")),
                Integer.parseInt(form.get("year")));

        movieRepo.save(movie);

        return "redirect:/catalog";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") long id) {
        movieRepo.deleteById(id);

        return "redirect:/catalog";
    }
}

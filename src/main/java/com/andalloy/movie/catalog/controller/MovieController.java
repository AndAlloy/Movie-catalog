package com.andalloy.movie.catalog.controller;

import com.andalloy.movie.catalog.model.Movie;
import com.andalloy.movie.catalog.model.MovieData;
import com.andalloy.movie.catalog.model.User;
import com.andalloy.movie.catalog.repository.MovieRepo;
import com.andalloy.movie.catalog.repository.UserRepo;
import com.andalloy.movie.catalog.service.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
public class MovieController {

    private final MovieRepo movieRepo;
    private final UserRepo userRepo;
    private final MovieService movieService;

    @Value("${image.dir}")
    private String directoryPath;

    public MovieController(MovieRepo movieRepo, UserRepo userRepo, MovieService movieService) {
        this.movieRepo = movieRepo;
        this.movieService = movieService;
        this.userRepo = userRepo;
    }

    @GetMapping("/catalog")
    public String catalog(@AuthenticationPrincipal UserDetails user, Model model) {
        List<Movie> movies = movieRepo.findAll();
        model.addAttribute("user", user);
        model.addAttribute("movies", movies);
        return "catalog";
    }

    @PostMapping("/catalog")
    public String addMovie(
            @RequestParam Map<String, String> form
    ) {
        String imdbId = form.get("imdbId");

        MovieData movieData = movieService.getMovieData(imdbId);
        movieRepo.save(getMovie(movieData));

        return "redirect:/catalog";
    }

    @GetMapping("/item/{id}")
    public String showMoviePage(
            @PathVariable("id") long id,
            Model model,
            @AuthenticationPrincipal User user
    ) {
        Movie movie = movieRepo.findById(id).orElseThrow(NoSuchElementException::new);

        model.addAttribute("movie", movie);
        model.addAttribute("user", user);
        return "item";
    }

    @PostMapping("/item/{id}")
    public String addComment(
            @PathVariable("id") long id,
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form
    ) {
        String comment = form.get("comment");
        System.out.println(comment);
        User foundUser = userRepo.findByName(user.getUsername()).orElseThrow(NoSuchElementException::new);
        Movie movie = movieRepo.findById(id).orElseThrow(NoSuchElementException::new);
        movie.getReview().put(foundUser.getId(), comment);

        movieRepo.save(movie);

        return "redirect:/item/"+id;
    }


    private Movie getMovie(MovieData md) {
        return new Movie(
                md.getId(),
                md.getTitle().getTitle(),
                md.getPlotSummary().getText(),
                md.getPlotOutline().getText(),
                md.getRatings().getRating(),
                md.getRatings().getRatingCount(),
                md.getTitle().getYear(),
                md.getTitle().getImage().getUrl()
        );
    }

    private String savePhoto(MultipartFile multipartFile) {
        File dir = Paths.get(directoryPath).toFile();

        if (!dir.exists()) {
            dir.mkdir();
        }


        String randomName = UUID.randomUUID().toString();
        String result = randomName + "." + multipartFile.getOriginalFilename();
        File newFile = Paths.get(directoryPath + "/" + result).toFile();

        try {
            multipartFile.transferTo(newFile);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return result;
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") long id) {
        movieRepo.deleteById(id);

        return "redirect:/catalog";
    }
}

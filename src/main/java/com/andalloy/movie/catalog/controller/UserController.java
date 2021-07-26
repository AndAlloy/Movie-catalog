package com.andalloy.movie.catalog.controller;

import com.andalloy.movie.catalog.model.User;
import com.andalloy.movie.catalog.repository.UserRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.NoSuchElementException;

@Controller
//@RequestMapping("/user")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/user/{id}")
    public String getInfo(@PathVariable("id") long userId,
                          Model model) {
        User user = userRepo.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        model.addAttribute("user", user);

        return "user";
    }

    @GetMapping("/myaccount")
    public String viewHome(
            @AuthenticationPrincipal UserDetails user,
             Model model
            ) {
        model.addAttribute("user", user);
        return "user";
    }

}

package com.andalloy.movie.catalog.controller;

import com.andalloy.movie.catalog.model.Role;
import com.andalloy.movie.catalog.model.User;
import com.andalloy.movie.catalog.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String getView() {
        return "registration";
    }

    @PostMapping
    public String registration(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("password_confirm") String passwordConfirm,
            Model model
    ) {
        if(!password.equals(passwordConfirm)) {
            model.addAttribute("error_message", "passwords not match");
            return "registration";
        }

        User user = new User(
                name == null ? "undefined" : name,
                email,
                passwordEncoder.encode(password),
                Role.USER
        );

        userRepo.save(user);

        return "redirect:/catalog";
    }
}

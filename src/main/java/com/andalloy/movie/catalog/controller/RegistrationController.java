package com.andalloy.movie.catalog.controller;

import com.andalloy.movie.catalog.model.Role;
import com.andalloy.movie.catalog.model.User;
import com.andalloy.movie.catalog.repository.UserRepo;
import com.andalloy.movie.catalog.service.MailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final MailService mailService;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepo userRepo, MailService mailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.mailService = mailService;
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
        String trim = name.trim();
        String confirmationCode = generateRandomCode();
        User user = new User(
                trim.isEmpty() ? "undefined" : trim,
                email,
                passwordEncoder.encode(password),
                confirmationCode,
                Role.USER
        );

        userRepo.save(user);

        mailService.sendConfirmCode(email, confirmationCode);

        return "redirect:/catalog";
    }

    @GetMapping("/confirm/{code}")
    public String confirm(@PathVariable String code, Model model) {
        Optional<User> user = userRepo.findByConfirmationCode(code);
        if (user.isPresent()) {
            User fromDb = user.get();
            fromDb.setConfirmationCode("confirmed");
            userRepo.save(fromDb);
            model.addAttribute("message", "Your account is activated");
        } else {
            model.addAttribute("message", "Your account is not activated");
        }
        return "login";
    }

    private String generateRandomCode() {
        return UUID.randomUUID().toString();
    }
}

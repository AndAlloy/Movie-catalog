package com.andalloy.movie.catalog.controller;

import com.andalloy.movie.catalog.model.Role;
import com.andalloy.movie.catalog.model.User;
import com.andalloy.movie.catalog.repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    private final UserRepo userRepo;

    public AdminController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);

        return "admin-control";
    }

    @GetMapping("/add-admin/{id}")
    public String makeAdmin(
            @PathVariable String id
    ) {
        long userId = Long.parseLong(id);
        User user = userRepo.findById(userId).orElseThrow(NoSuchElementException::new);
        user.setRole(Role.ADMIN);
        userRepo.save(user);

        return "redirect:/admin";
    }

    @GetMapping("/delete-admin/{id}")
    public String deleteAdmin(
            @PathVariable String id
    ) {
        long userId = Long.parseLong(id);
        User user = userRepo.findById(userId).orElseThrow(NoSuchElementException::new);
        user.setRole(Role.USER);
        userRepo.save(user);

        return "redirect:/admin";
    }
}

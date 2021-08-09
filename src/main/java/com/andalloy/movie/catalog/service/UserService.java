package com.andalloy.movie.catalog.service;

import com.andalloy.movie.catalog.model.User;
import com.andalloy.movie.catalog.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserFromDb(User user) {
        return userRepo.findByEmail(user.getEmail()).orElseThrow(NoSuchElementException::new);
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }
}

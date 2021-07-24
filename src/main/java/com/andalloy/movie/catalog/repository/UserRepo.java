package com.andalloy.movie.catalog.repository;

import com.andalloy.movie.catalog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}

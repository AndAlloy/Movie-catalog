package com.andalloy.movie.catalog.repository;

import com.andalloy.movie.catalog.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
}

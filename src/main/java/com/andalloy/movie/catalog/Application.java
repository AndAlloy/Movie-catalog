package com.andalloy.movie.catalog;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class Application {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}

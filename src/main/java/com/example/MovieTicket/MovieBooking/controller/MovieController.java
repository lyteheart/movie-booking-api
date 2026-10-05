package com.example.MovieTicket.MovieBooking.controller;


import com.example.MovieTicket.MovieBooking.Model.Movie;
import com.example.MovieTicket.MovieBooking.service.MovieServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class MovieController {

    private final MovieServiceInterface movieService;

    public MovieController(MovieServiceInterface movieService) {
        this.movieService = movieService;
    }

    // GET /ticket/movies
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // POST /ticket/movie
    @PostMapping("/movie")
    public ResponseEntity<?> addMovie(
            @Valid @RequestBody Movie movie,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RuntimeException("Invalid movie details");
        }

        movieService.addMovie(movie);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    // GET /ticket/movie/{id}
    @GetMapping("/movie/{id}")
    public Movie getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    // DELETE /ticket/movie/{id}
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable String id) {

        movieService.deleteMovieById(id);

        return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
    }

    // PUT /ticket/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMovie(
            @Valid @RequestBody Movie movie,
            @PathVariable String id,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RuntimeException("Invalid movie details");
        }

        movieService.updateMovie(id, movie);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}

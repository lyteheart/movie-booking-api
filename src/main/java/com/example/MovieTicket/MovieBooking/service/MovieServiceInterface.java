package com.example.MovieTicket.MovieBooking.service;

import com.example.MovieTicket.MovieBooking.Model.Movie;

import java.util.List;

public interface MovieServiceInterface {

    List<Movie> getAllMovies();

    void addMovie(Movie movie);

    Movie getMovieById(String id);

    void deleteMovieById(String id);

    void updateMovie(String id, Movie movie);
}

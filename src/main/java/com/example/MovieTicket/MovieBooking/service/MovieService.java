package com.example.MovieTicket.MovieBooking.service;


import com.example.MovieTicket.MovieBooking.Exceptions.IdAlreadyExist;
import com.example.MovieTicket.MovieBooking.Exceptions.IdNotFound;
import com.example.MovieTicket.MovieBooking.Model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService implements MovieServiceInterface {

    List<Movie> movieList = new ArrayList<>();
    Map<String, Movie> movieMap = new HashMap<>();



    @Override
    public List<Movie> getAllMovies() {
        return movieList;
    }

    @Override
    public void addMovie(Movie movie) {

        if (movieMap.containsKey(movie.getId())) {
            throw new IdAlreadyExist(
                    "Movie with id " + movie.getId() + " already exists"
            );
        }

        movieList.add(movie);
        movieMap.put(movie.getId(), movie);
    }

    @Override
    public Movie getMovieById(String id) {

        if (!movieMap.containsKey(id)) {
            throw new IdNotFound("Movie with id " + id + " not found");
        }

        return movieMap.get(id);
    }

    @Override
    public void deleteMovieById(String id) {

        Movie movie = getMovieById(id);

        movieList.remove(movie);
        movieMap.remove(id);
    }

    @Override
    public void updateMovie(String id, Movie movie) {

        if (movie == null) {
            throw new RuntimeException("Movie body is invalid");
        }

        Movie existingMovie = getMovieById(id);

        movieList.remove(existingMovie);
        movieList.add(movie);

        movieMap.put(id, movie);
    }
}
package com.example.demo.service;

import com.example.demo.model.Genre;
import com.example.demo.model.Movie;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.MovieRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Movie createMovie(String title, int year, String genreName) {
        Genre genre = genreRepository.findById(genreName).orElseGet(() -> {
            Genre newGenre = new Genre(genreName);
            return genreRepository.save(newGenre);
        });

        Movie movie = new Movie(title, year, genre);
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}

package com.example.demo.services;

import com.example.demo.models.Genre;
import com.example.demo.models.Movie;
import com.example.demo.repositories.GenreRepository;
import com.example.demo.repositories.MovieRepository;

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

    public Movie updateMovie(Long id, String title, int year, String genreName) {
        // Verifica se o filme existe
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        // Busca ou cria o gênero
        Genre genre = genreRepository.findById(genreName)
                .orElseGet(() -> genreRepository.save(new Genre(genreName)));

        // Atualiza os dados do filme
        existingMovie.setTitle(title);
        existingMovie.setYear(year);
        existingMovie.setGenre(genre);

        // Salva as alterações
        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);
    }
}

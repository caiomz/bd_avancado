package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Genre;
import com.example.demo.models.Movie;

@Service
public class UserService {

    @Autowired
    private Driver driver;

    @SuppressWarnings("deprecation")
    public List<Movie> getWatchedMovies(String userId) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                List<Movie> movies = new ArrayList<>();
                Result result = tx.run("""
                        MATCH (u:User {id: $userId})-[:WATCHED]->(m:Movie)-[:HAS_GENRE]->(g:Genre)
                        RETURN ID(m) AS id, m.title AS title, m.year AS year, g.name AS genre
                        """, Map.of("userId", userId));

                while (result.hasNext()) {
                    Record record = result.next();

                    Long id = record.get("id").asLong();
                    String title = record.get("title").asString();
                    int year = record.get("year").asInt();
                    String genreName = record.get("genre").asString();

                    Genre genre = new Genre(genreName);
                    Movie movie = new Movie(title, year, genre);
                    movie.setId(id);

                    movies.add(movie);
                }

                return movies;
            });
        }
    }
}

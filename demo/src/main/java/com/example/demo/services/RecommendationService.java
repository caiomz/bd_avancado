package com.example.demo.services;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Service;

import com.example.demo.models.Genre;
import com.example.demo.models.Movie;

import java.util.*;

@Service
public class RecommendationService {

    private final Driver driver;

    public RecommendationService(Driver driver) {
        this.driver = driver;
    }

    @SuppressWarnings("deprecation")
    public List<Movie> watchMovieAndRecommend(String userId, String movieTitle) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("""
                        MATCH (u:User {id: $userId}), (m:Movie {title: $movieTitle})-[:HAS_GENRE]->(g:Genre)
                        MERGE (u)-[:WATCHED]->(m)
                        MERGE (u)-[:LIKES]->(g)
                        """, Map.of("userId", userId, "movieTitle", movieTitle));
                return null;
            });
            return recommendMovies(userId);
        }
    }

    @SuppressWarnings("deprecation")
    public List<Movie> recommendMovies(String userId) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                List<Movie> movies = new ArrayList<>();
                Result result = tx.run("""
                        MATCH (u:User {id: $userId})-[:LIKES]->(g:Genre)<-[:HAS_GENRE]-(m:Movie)
                        WHERE NOT (u)-[:WATCHED]->(m)
                        RETURN m.title AS title, m.year AS year, g.name AS genre
                        LIMIT 10
                        """, Map.of("userId", userId));

                while (result.hasNext()) {
                    Record record = result.next();

                    String title = record.containsKey("title") ? record.get("title").asString() : "Desconhecido";
                    int year = record.containsKey("year") ? record.get("year").asInt() : 0;
                    String genreName = record.containsKey("genre") ? record.get("genre").asString() : "Desconhecido";

                    Genre genre = new Genre(genreName);
                    movies.add(new Movie(title, year, genre));
                }

                return movies;
            });
        }
    }

}

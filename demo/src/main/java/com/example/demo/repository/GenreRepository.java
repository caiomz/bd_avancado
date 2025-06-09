package com.example.demo.repository;

import com.example.demo.model.Genre;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GenreRepository extends Neo4jRepository<Genre, String> {
}

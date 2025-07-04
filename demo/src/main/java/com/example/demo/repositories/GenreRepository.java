package com.example.demo.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.models.Genre;

public interface GenreRepository extends Neo4jRepository<Genre, String> {
}

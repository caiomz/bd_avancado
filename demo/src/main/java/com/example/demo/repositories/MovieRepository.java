package com.example.demo.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.models.Movie;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {
}

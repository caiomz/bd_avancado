package com.example.demo.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.models.User;

public interface UserRepository extends Neo4jRepository<User, String> {
    User findByUsername(String username);
}

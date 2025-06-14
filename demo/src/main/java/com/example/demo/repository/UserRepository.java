package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.User;

public interface UserRepository extends Neo4jRepository<User, String> {
    User findByUsername(String username);
}

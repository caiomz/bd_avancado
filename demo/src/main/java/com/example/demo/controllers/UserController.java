package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Movie;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository repo;
    private final UserService userService;

    public UserController(UserRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return repo.findAll();
    }

    @GetMapping("/{userId}/watched")
    public ResponseEntity<List<Movie>> getWatchedMovies(@PathVariable String userId) {
        List<Movie> movies = userService.getWatchedMovies(userId);
        return ResponseEntity.ok(movies);
    }
}

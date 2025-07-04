package com.example.demo.controllers;

import com.example.demo.models.Movie;
import com.example.demo.services.RecommendationService;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RecommendationController {
    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping("/watch")
    public List<Movie> watchMovie(@RequestBody Map<String, String> body) {
        String userId = body.get("userId");
        String movieTitle = body.get("movieTitle");
        return service.watchMovieAndRecommend(userId, movieTitle);
    }

    @GetMapping("/recommendations/{userId}")
    public List<Movie> getRecommendations(@PathVariable String userId) {
        return service.recommendMovies(userId);
    }
}

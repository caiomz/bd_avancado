package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.*;

@Node
public class Movie {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private int year;

    @Relationship(type = "HAS_GENRE", direction = Relationship.Direction.OUTGOING)
    private Genre genre;

    public Movie() {
    }

    public Movie(String title, int year, Genre genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}

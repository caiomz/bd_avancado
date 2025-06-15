package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

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

    // Construtor sem ID (para criação)
    public Movie(String title, int year, Genre genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    // Construtor com ID (útil para queries manuais com Neo4j Driver)
    public Movie(Long id, String title, int year, Genre genre) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    // Setter adicionado para poder setar o ID manualmente quando necessário
    public void setId(Long id) {
        this.id = id;
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

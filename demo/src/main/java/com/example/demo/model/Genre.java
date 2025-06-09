package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.*;

@Node
public class Genre {
    @Id
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
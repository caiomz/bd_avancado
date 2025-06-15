package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import java.util.Set;

@Node
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private Set<String> roles;

    public User() {
    }

    public User(String id, String username, String password, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

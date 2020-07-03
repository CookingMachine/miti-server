package com.miti.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "usr")
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"recipeList", "commentList", "favouriteList"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;

    private boolean active;
    private String role;

    @OneToMany(mappedBy = "author")
    private List<Recipe> recipeList;

    @OneToMany(mappedBy = "commentator")
    private List<Comment> commentList;

    @ManyToMany(mappedBy = "favouriteUsers")
    private List<Recipe> favouriteList;

    public User() { }

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.active = true;
        this.role = role;
    }
}

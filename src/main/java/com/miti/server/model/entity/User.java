package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"recipeList", "commentList", "favouriteList"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean status;
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Recipe> recipeList;

    @OneToMany(mappedBy = "commentator")
    private List<Comment> commentList;

    @ManyToMany(mappedBy = "favouriteUsers")
    private List<Recipe> favouriteList;

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = true;
        this.role = role;
    }
}

package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.model.dto.UserDTO;
import com.miti.server.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "usr")
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"recipeList", "commentList", "favouriteList"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private boolean isActive = true;

    private UserRole role;

    @OneToMany(mappedBy = "author")
    private List<Recipe> recipeList;

    @OneToMany(mappedBy = "commentator")
    private List<Comment> commentList;

    @ManyToMany(mappedBy = "favouriteUsers")
    private List<Recipe> favouriteList;

    public User(UserDTO dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.isActive = true;
        this.role = dto.getRole();
        this.email = dto.getEmail();
    }
}

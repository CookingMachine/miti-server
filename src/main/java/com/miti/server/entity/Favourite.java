package com.miti.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true,
    value = {"user", "favouriteRecipe"})
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long recipeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usrId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe", nullable = false)
    private Recipe favouriteRecipe;

    public Favourite() {}

    public Favourite(User user, Recipe favouriteRecipe, Long userId, Long recipeId) {
        this.user = user;
        this.favouriteRecipe = favouriteRecipe;
        this.userId = userId;
        this.recipeId = recipeId;
    }
}

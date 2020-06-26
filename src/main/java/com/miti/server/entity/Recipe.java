package com.miti.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usrId", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "recipeIngredients")
    private List<IngredientContext> ingredientContextList;

    @OneToMany(mappedBy = "favouriteRecipe")
    private List<Favourite> favouriteList;

    public Recipe() {
    }

    public Recipe(String name, String description, User author, Category category) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.category = category;
    }
}

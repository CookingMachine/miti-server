package com.miti.server.entity;

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

    @OneToMany(mappedBy = "recipe")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "recipeIngredients")
    private List<IngredientContext> ingredientContextList;

    public Recipe() {
    }

    public Recipe(String name, String description, User author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }
}

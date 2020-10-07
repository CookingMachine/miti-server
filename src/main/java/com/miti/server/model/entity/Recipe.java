package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "recipe_table")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"commentList", "ingredientContextList", "favouriteUsers"})
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Size(max = 4000)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "recipeIngredients")
    private List<IngredientContext> ingredientContextList;

    @ManyToMany
    private List<User> favouriteUsers;

    public Recipe(String name, String description, User author, Category category) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.category = category;
    }
}

package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.model.dto.RecipeDTO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "recipe")
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"commentList", "ingredientContextList", "favouriteUsers"})
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Size(max = 4000)
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

    @ManyToMany
    private List<User> favouriteUsers;

    public Recipe() {
    }

    public Recipe(RecipeDTO dto){
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.author = dto.getAuthor();
        this.category = dto.getCategory();
    }
}

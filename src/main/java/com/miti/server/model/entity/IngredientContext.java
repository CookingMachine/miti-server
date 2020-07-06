package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"ingredients", "recipeIngredients"})
public class IngredientContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double count;
    private String flag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredientId", nullable = false)
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipeId", nullable = false)
    private Recipe recipeIngredients;

    public IngredientContext() { }

    public IngredientContext(double count, String flag, Recipe recipeIngredients, Ingredient ingredient) {
        this.count = count;
        this.flag = flag;
        this.ingredient = ingredient;
        this.recipeIngredients = recipeIngredients;
    }
}

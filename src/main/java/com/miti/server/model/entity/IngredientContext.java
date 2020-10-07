package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.enums.Measure;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ingredientContext_table")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"ingredients", "recipeIngredients"})
public class IngredientContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;
    private Measure measure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredientId", nullable = false)
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipeId", nullable = false)
    private Recipe recipeIngredients;

    public IngredientContext(Integer amount, Measure measure, Ingredient ingredient, Recipe recipe) {
        this.amount = amount;
        this.measure = measure;
        this.ingredient = ingredient;
        this.recipeIngredients = recipe;
    }
}

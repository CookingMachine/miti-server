package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.enums.Measure;
import com.miti.server.model.dto.IngredientContextDTO;
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

    private int amount;
    private Measure measure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredientId", nullable = false)
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipeId", nullable = false)
    private Recipe recipeIngredients;

    public IngredientContext() { }

    public IngredientContext(IngredientContextDTO dto) {
        this.amount = dto.getAmount();
        this.measure = dto.getMeasure();
        this.ingredient = dto.getIngredient();
        this.recipeIngredients = dto.getRecipe();
    }
}

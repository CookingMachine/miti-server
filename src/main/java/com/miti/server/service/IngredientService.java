package com.miti.server.service;

import com.miti.server.model.dto.IngredientDTO;
import com.miti.server.model.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient addIngredient(IngredientDTO ingredientDTO);

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredientByName(String name);

    List<Ingredient> getAllIngredients();

    List<Ingredient> getIngredientsByCategory(String category);

    Ingredient getIngredientById(String id);
}

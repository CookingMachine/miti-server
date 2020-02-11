package com.miti.server.service;

import com.miti.server.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient addIngredient(String name);

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredientByName(String name);

    List<Ingredient> getAllIngredients();
}

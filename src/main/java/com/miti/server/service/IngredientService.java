package com.miti.server.service;

import com.miti.server.model.entity.Ingredient;

import java.util.List;

public interface IngredientService {
  Ingredient addIngredient(Ingredient ingredient);
  void addAllIngredients(List<Ingredient> ingredients);

  Ingredient getIngredientById(String ingredientId);
  Ingredient getIngredientByName(String name);

  List<Ingredient> getIngredientsByCategory(String categoryName);

  void deleteIngredientById(String ingredientId);
}

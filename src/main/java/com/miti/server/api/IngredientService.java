package com.miti.server.api;

import com.miti.server.model.entity.Ingredient;

import java.util.List;

public interface IngredientService {

  Ingredient addIngredient(Ingredient ingredient);

  void addAllIngredients(List<Ingredient> ingredients);

  Ingredient editIngredient(String id, Ingredient newIngredient);

  Ingredient getIngredientById(String ingredientId);

  Ingredient getIngredientByName(String name);

  List<Ingredient> getAllIngredients();

  List<Ingredient> getIngredientsByCategory(String categoryName);

  void deleteIngredientById(String ingredientId);
}

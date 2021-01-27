package com.miti.server.service;

import com.miti.server.enums.Kitchen;
import com.miti.server.model.entity.Recipe;

import java.util.List;

public interface RecipeService {
  Recipe addRecipe(Recipe recipe);
  void addAllRecipes(List<Recipe> recipes);

  Recipe editRecipe(Long recipeId, Recipe newRecipe);

  Recipe getRecipeById(Long recipeId);
  Recipe getRecipeByName(String name);

  List<Recipe> getAllRecipes();
  List<Recipe> getRecipesByAuthorId(Long authorId);
  List<Recipe> getRecipesByCategoryId(String categoryId);
  List<Recipe> getRecipesByKitchen(String kitchenName);

  void deleteRecipeById(Long recipeId);
  void deleteCommentById(Long commentId);
  void deleteIngredientContextById(Long id);
}

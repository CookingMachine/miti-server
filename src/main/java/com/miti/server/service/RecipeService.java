package com.miti.server.service;

import com.miti.server.model.entity.Recipe;

import java.util.List;

public interface RecipeService {
  Recipe addRecipe(Recipe recipe);
  void addAllRecipes(List<Recipe> recipes);

  Recipe getRecipeById(Long recipeId);
  Recipe getRecipeByName(String name);

  List<Recipe> getAllRecipes();
  List<Recipe> getRecipesByAuthorId(Long authorId);
  List<Recipe> getRecipesByCategoryId(String categoryId);

  void deleteRecipeById(Long recipeId);
  void deleteCommentById(Long commentId);
  void deleteIngredientContextById(Long id);
}

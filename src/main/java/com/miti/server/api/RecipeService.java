package com.miti.server.api;

import com.miti.server.model.entity.CalorieContent;
import com.miti.server.model.entity.Recipe;

import java.util.Date;
import java.util.List;

public interface RecipeService {

  Recipe addRecipe(Recipe recipe);

  void addAllRecipes(List<Recipe> recipes);

  Recipe editRecipe(Recipe newRecipe);

  Recipe getRecipeById(Long recipeId);

  List<Recipe> getRecipeByName(String name);

  Recipe getRecipeByCalorie(CalorieContent calorieContent);

  List<Recipe> getAllRecipes();

  List<Recipe> getRecipesByAuthorId(Long authorId);

  List<Recipe> getRecipesByCategoryId(String categoryId);

  List<Recipe> getRecipesByKitchen(String kitchenName);

  List<Recipe> getRecipesByTimeLessThanEqual(int time);

  List<Recipe> getRecipesByCreateDateBetween(Date recipePublicationDateStart, Date today);

  void deleteRecipeById(Long recipeId);
}

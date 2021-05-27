package com.miti.server.service;

import com.miti.data.model.CalorieContent;
import com.miti.data.model.Recipe;
import com.miti.server.api.request.RecipeRequest;
import com.miti.server.api.response.RecipeResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface RecipeService {

  RecipeResponse addRecipe(RecipeRequest recipeRequest);

  void addAllRecipes(List<Recipe> recipes);

  Recipe editRecipe(Recipe newRecipe);

  Recipe getRecipeById(long recipeId);

  List<Recipe> getRecipeByName(String name);

  Recipe getRecipeByCalorie(CalorieContent calorieContent);

  List<Recipe> getAllRecipes();

  List<Recipe> getRecipesByAuthorId(long authorId);

  List<Recipe> getRecipesByCategoryId(String categoryId);

  List<Recipe> getRecipesByKitchen(String kitchenName);

  List<Recipe> getRecipesByTimeLessThanEqual(int time);

  List<Recipe> getRecipesByCreateDateBetween(LocalDateTime recipePublicationDateStart);

  List<Recipe> getFavouriteRecipesByUserId(Long userId);

  void deleteRecipeById(Long recipeId);
}

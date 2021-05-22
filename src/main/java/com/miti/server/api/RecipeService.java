package com.miti.server.api;

import com.miti.server.model.entity.CalorieContent;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.request.RecipeRequest;
import com.miti.server.model.response.RecipeResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface RecipeService {

  RecipeResponse addRecipe(RecipeRequest recipeRequest);

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

  List<Recipe> getRecipesByCreateDateBetween(LocalDateTime recipePublicationDateStart);

  void deleteRecipeById(Long recipeId);
}

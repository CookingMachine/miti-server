package com.miti.server.service;

import com.miti.server.entity.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipeById(Long recipeId);

    List<Recipe> getAllRecipes();

    List<Recipe> getRecipesByName(String name);

    List<Recipe> getRecipesByAuthor(String author);
}

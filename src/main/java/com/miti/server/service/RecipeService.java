package com.miti.server.service;

import com.miti.server.entity.Category;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;

import java.util.List;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe addRecipe(String name, String description, User author, Category category);

    Recipe getRecipeById(Long recipeId);

    List<Recipe> getAllRecipes();

    List<Recipe> getRecipesByAuthor(String author);

    List<Recipe> getRecipesByAuthorId(Long id);

    List<Recipe> getRecipesByCategory(String category);

    List<Recipe> getRecipesByCategoryId(Long categoryId);
}

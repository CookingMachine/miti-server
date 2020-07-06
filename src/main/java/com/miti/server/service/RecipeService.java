package com.miti.server.service;

import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;

import java.util.List;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe addRecipe(String name, String description, User author, Category category);

    Recipe getRecipeById(Long recipeId);

    List<Recipe> getAllRecipes();

    List<Recipe> getRecipesByAuthor(User author);

    List<Recipe> getRecipesByAuthorId(Long id);

    List<Recipe> getRecipesByCategory(Category category);

    List<Recipe> getRecipesByCategoryId(String categoryId);
}

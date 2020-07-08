package com.miti.server.service;

import com.miti.server.model.dto.RecipeDTO;
import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import com.miti.server.model.form.RecipeForm;

import java.util.List;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe addRecipe(RecipeDTO recipeDTO);

    Recipe getRecipeById(Long recipeId);

    List<Recipe> getAllRecipes();

    List<Recipe> getRecipesByAuthor(User author);

    List<Recipe> getRecipesByAuthorId(Long id);

    List<Recipe> getRecipesByCategory(Category category);

    List<Recipe> getRecipesByCategoryId(String categoryId);

    boolean checkFieldsExist(Long userId, String categoryId);

    Recipe addRecipeDTO(RecipeForm recipeForm);
}

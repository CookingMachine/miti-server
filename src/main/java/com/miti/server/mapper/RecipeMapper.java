package com.miti.server.mapper;

import com.miti.server.model.entity.Recipe;
import com.miti.server.model.enums.Kitchen;
import com.miti.server.model.request.RecipeRequest;
import com.miti.server.model.response.RecipeResponse;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {

  public Recipe recipeRequestToRecipeModel(RecipeRequest recipeRequest) {
    return recipeRequest == null ? null :
        new Recipe(recipeRequest.getName(),
            recipeRequest.getDescription(),
            recipeRequest.getAuthor(),
            recipeRequest.getCategory(),
            Kitchen.valueOf(recipeRequest.getKitchen()),
            recipeRequest.getTime(),
            recipeRequest.getCalorieContent());
  }

  public RecipeResponse recipeModelToRecipeResponse(Recipe recipe) {
    return recipe == null ? null :
        new RecipeResponse(recipe.getName(),
            recipe.getDescription(),
            recipe.getTime(),
            recipe.getKitchen().name(),
            recipe.getAuthor(),
            recipe.getCategory(),
            recipe.getContextIngredientList(),
            recipe.getCalorie(),
            recipe.getImageUrl());
  }
}


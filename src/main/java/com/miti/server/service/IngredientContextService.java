package com.miti.server.service;

import com.miti.server.entity.Ingredient;
import com.miti.server.entity.IngredientContext;
import com.miti.server.entity.Recipe;

import java.util.List;

public interface IngredientContextService {
    IngredientContext getIngredientContextByRecipeId(Long recipeId);

    List<IngredientContext> getAllIngredientContexts();

    IngredientContext addIngredientContext(double count, String flag, Recipe recipe, Ingredient ingredient);

    IngredientContext addIngredientContext(IngredientContext ingredientContext);
}

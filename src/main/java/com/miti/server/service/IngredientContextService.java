package com.miti.server.service;

import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.IngredientContext;
import com.miti.server.model.entity.Recipe;

import java.util.List;

public interface IngredientContextService {
    List<IngredientContext> getIngredientContextByRecipeId(Long recipeId);

    List<IngredientContext> getAllIngredientContexts();

    IngredientContext addIngredientContext(double count, String flag, Recipe recipe, Ingredient ingredient);

    IngredientContext addIngredientContext(IngredientContext ingredientContext);

    IngredientContext getIngredientContextById(Long id);
}

package com.miti.server.service;

import com.miti.server.model.dto.IngredientContextDTO;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.IngredientContext;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.form.IngredientContextForm;

import java.util.List;

public interface IngredientContextService {
    List<IngredientContext> getIngredientContextByRecipeId(Long recipeId);

    List<IngredientContext> getAllIngredientContexts();

    IngredientContext addIngredientContext(IngredientContextDTO dto);

    IngredientContext addIngredientContextDTO(IngredientContextForm form);

    IngredientContext addIngredientContext(IngredientContext ingredientContext);

    IngredientContext getIngredientContextById(Long id);

    boolean checkFieldsExist(Long recipeId, String ingredientId);
}

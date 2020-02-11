package com.miti.server.service.impl;

import com.miti.server.entity.Ingredient;
import com.miti.server.entity.IngredientContext;
import com.miti.server.entity.Recipe;
import com.miti.server.repo.IngredientContextRepo;
import com.miti.server.service.IngredientContextService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientContextServiceImpl implements IngredientContextService {
    private final IngredientContextRepo ingredientContextRepo;

    public IngredientContextServiceImpl(IngredientContextRepo ingredientContextRepo) {
        this.ingredientContextRepo = ingredientContextRepo;
    }

    @Override
    public IngredientContext getIngredientContextByRecipeId(Long recipeId) {
        return ingredientContextRepo.getIngredientContextByRecipeIngredients(recipeId);
    }

    @Override
    public List<IngredientContext> getAllIngredientContexts() {
        return ingredientContextRepo.findAll();
    }

    @Override
    public IngredientContext addIngredientContext(double count, String flag, Recipe recipe, Ingredient ingredient) {
        IngredientContext ingredientContext = new IngredientContext(count, flag, recipe, ingredient);
        return addIngredientContext(ingredientContext);
    }

    @Override
    public IngredientContext addIngredientContext(IngredientContext ingredientContext) {
        return ingredientContextRepo.save(ingredientContext);
    }
}

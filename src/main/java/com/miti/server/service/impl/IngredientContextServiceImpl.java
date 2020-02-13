package com.miti.server.service.impl;

import com.miti.server.entity.Ingredient;
import com.miti.server.entity.IngredientContext;
import com.miti.server.entity.Recipe;
import com.miti.server.repo.IngredientContextRepo;
import com.miti.server.repo.RecipeRepo;
import com.miti.server.service.IngredientContextService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientContextServiceImpl implements IngredientContextService {
    private final IngredientContextRepo ingredientContextRepo;
    private final RecipeRepo recipeRepo;

    public IngredientContextServiceImpl(IngredientContextRepo ingredientContextRepo, RecipeRepo recipeRepo) {
        this.ingredientContextRepo = ingredientContextRepo;
        this.recipeRepo = recipeRepo;
    }

    @Override
    public List<IngredientContext> getIngredientContextByRecipeId(Long recipeId) {
        Recipe recipe = recipeRepo.findById(recipeId).orElseThrow(()->
                new RuntimeException("Recipe with id: " + recipeId + " is not find!"));
        List<IngredientContext> ingredients = recipe.getIngredientContextList();
        return ingredients;
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

    @Override
    public IngredientContext getIngredientContextById(Long id) {
        return ingredientContextRepo.findById(id).orElseThrow(()->
                new RuntimeException("IngredientContext with id: " + id + " is not found"));
    }
}

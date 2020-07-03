package com.miti.server.service.impl;

import com.miti.server.entity.Ingredient;
import com.miti.server.entity.IngredientContext;
import com.miti.server.entity.Recipe;
import com.miti.server.repository.IngredientContextRepository;
import com.miti.server.repository.RecipeRepository;
import com.miti.server.service.IngredientContextService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientContextServiceImpl implements IngredientContextService {
    private final IngredientContextRepository ingredientContextRepository;
    private final RecipeRepository recipeRepository;

    public IngredientContextServiceImpl(IngredientContextRepository ingredientContextRepository, RecipeRepository recipeRepository) {
        this.ingredientContextRepository = ingredientContextRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<IngredientContext> getIngredientContextByRecipeId(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->
                new RuntimeException("Recipe with id: " + recipeId + " is not find!"));
        List<IngredientContext> ingredients = recipe.getIngredientContextList();
        return ingredients;
    }

    @Override
    public List<IngredientContext> getAllIngredientContexts() {
        return ingredientContextRepository.findAll();
    }

    @Override
    public IngredientContext addIngredientContext(double count, String flag, Recipe recipe, Ingredient ingredient) {
        IngredientContext ingredientContext = new IngredientContext(count, flag, recipe, ingredient);
        return addIngredientContext(ingredientContext);
    }

    @Override
    public IngredientContext addIngredientContext(IngredientContext ingredientContext) {
        return ingredientContextRepository.save(ingredientContext);
    }

    @Override
    public IngredientContext getIngredientContextById(Long id) {
        return ingredientContextRepository.findById(id).orElseThrow(()->
                new RuntimeException("IngredientContext with id: " + id + " is not found"));
    }
}

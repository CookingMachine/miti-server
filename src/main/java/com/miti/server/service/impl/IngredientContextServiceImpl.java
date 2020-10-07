package com.miti.server.service.impl;

import com.miti.server.enums.Measure;
import com.miti.server.model.dto.IngredientContextDTO;
import com.miti.server.model.dto.IngredientDTO;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.IngredientContext;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.form.IngredientContextForm;
import com.miti.server.repository.IngredientContextRepository;
import com.miti.server.repository.RecipeRepository;
import com.miti.server.service.IngredientContextService;
import com.miti.server.service.IngredientService;
import com.miti.server.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IngredientContextServiceImpl implements IngredientContextService {

    private final IngredientContextRepository ingredientContextRepository;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @Override
    public List<IngredientContext> getIngredientContextByRecipeId(Long recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        List<IngredientContext> ingredients = recipe.getIngredientContextList();
        return ingredients;
    }

    @Override
    public List<IngredientContext> getAllIngredientContexts() {
        return ingredientContextRepository.findAll();
    }

    @Override
    public IngredientContext addIngredientContext(IngredientContextDTO dto) {
        return addIngredientContext(new IngredientContext(dto));
    }

    @Override
    public IngredientContext addIngredientContextDTO(IngredientContextForm form) {
        int amount = form.getAmount();
        Measure measure = form.getMeasure();
        Recipe recipe = recipeService.getRecipeById(form.getRecipeId());
        Ingredient ingredient = ingredientService.getIngredientById(form.getIngredientId());
        IngredientContextDTO dto = new IngredientContextDTO(amount, measure, ingredient, recipe);
        return addIngredientContext(dto);
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

    @Override
    public boolean checkFieldsExist(Long recipeId, String ingredientId) {
        if (recipeService.getRecipeById(recipeId) != null &&
                ingredientService.getIngredientById(ingredientId) != null)
            return true;
        else
            return false;
    }
}

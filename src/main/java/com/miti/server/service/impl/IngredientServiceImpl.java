package com.miti.server.service.impl;

import com.miti.server.entity.Ingredient;
import com.miti.server.repo.IngredientRepo;
import com.miti.server.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final IngredientRepo ingredientRepo;

    public IngredientServiceImpl(IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient addIngredient(String name) {
        Ingredient ingredient = new Ingredient(name);
        return addIngredient(ingredient);
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        return ingredientRepo.findIngredientByName(name);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepo.findAll();
    }

}

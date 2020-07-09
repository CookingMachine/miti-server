package com.miti.server.service.impl;

import com.miti.server.enums.IngredientCategory;
import com.miti.server.model.dto.IngredientDTO;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.repository.IngredientRepository;
import com.miti.server.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient addIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient(ingredientDTO);
        return addIngredient(ingredient);
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findIngredientByName(name);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public List<Ingredient> getIngredientsByCategory(String category) {
        IngredientCategory _category = IngredientCategory.UNKNOWN;

        for (IngredientCategory ingredientCategory : IngredientCategory.values()) {
            if (category.equals(ingredientCategory.name())) {
                _category = ingredientCategory;
            }
        }

        return ingredientRepository.getIngredientsByCategory(_category);
    }

}

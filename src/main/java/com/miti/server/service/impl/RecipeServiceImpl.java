package com.miti.server.service.impl;

import com.miti.server.entity.Recipe;
import com.miti.server.repo.RecipeRepo;
import com.miti.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepo recipeRepo;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        Recipe newRecipe = new Recipe(recipe.getName(),
                recipe.getDescription(),
                recipe.getAuthor());

        return recipeRepo.save(newRecipe);
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepo.findById(recipeId).orElseThrow(()
                -> new RuntimeException("Recipe with id: " + recipeId + " doesnt exist!"));
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = recipeRepo.findAll();
        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByName(String name) {
        List<Recipe> recipes = recipeRepo.getRecipesByName(name);
        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByAuthor(String author) {
        List<Recipe> recipes = recipeRepo.getRecipesByAuthor(author);
        return recipes;
    }
}

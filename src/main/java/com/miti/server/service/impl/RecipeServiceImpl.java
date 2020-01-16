package com.miti.server.service.impl;

import com.miti.server.entity.Recipe;
import com.miti.server.repo.RecipeRepo;
import com.miti.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepo recipeRepo;

    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepo.findById(recipeId).orElseThrow(()
                -> new RuntimeException("Recipe with id: " + recipeId + " doesnt exist!"));
    }


}

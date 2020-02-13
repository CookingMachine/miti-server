package com.miti.server.service.impl;

import com.miti.server.entity.Category;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;
import com.miti.server.repo.CategoryRepo;
import com.miti.server.repo.RecipeRepo;
import com.miti.server.repo.UserRepo;
import com.miti.server.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepo recipeRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;

    public RecipeServiceImpl(RecipeRepo recipeRepo, UserRepo userRepo, CategoryRepo categoryRepo) {
        this.recipeRepo = recipeRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepo.save(recipe);
    }

    @Override
    public Recipe addRecipe(String name, String description, User author, Category category) {
        Recipe recipe = new Recipe(name, description, author, category);
        return addRecipe(recipe);
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
    public List<Recipe> getRecipesByAuthor(String author) {
        User user = userRepo.getUserByUserName(author);
        List<Recipe> recipes = recipeRepo.getRecipesByAuthor(user);
        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByCategory(String name) {
        Category category = categoryRepo.getCategoryByName(name);
        List<Recipe> recipes = recipeRepo.getRecipesByCategory(category);
        return recipes;
    }
}

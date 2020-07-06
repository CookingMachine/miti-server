package com.miti.server.service.impl;

import com.miti.server.model.dto.RecipeDTO;
import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import com.miti.server.repository.CategoryRepository;
import com.miti.server.repository.RecipeRepository;
import com.miti.server.repository.UserRepository;
import com.miti.server.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe addRecipe(String name, String description, User author, Category category) {
        Recipe recipe = new Recipe(new RecipeDTO(name, description, author, category));
        return addRecipe(recipe);
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(()
                -> new RuntimeException("Recipe with id: " + recipeId + " doesnt exist!"));
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByAuthor(User author) {;
        List<Recipe> recipes = recipeRepository.getRecipesByAuthor(author);
        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByAuthorId(Long id) {
        User _user = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User with id: " + id + " doesn't exist!"));
        return recipeRepository.getRecipesByAuthor(_user);
    }

    @Override
    public List<Recipe> getRecipesByCategory(Category category) {
        List<Recipe> recipes = recipeRepository.getRecipesByCategory(category);
        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByCategoryId(String categoryId) {
        return recipeRepository.getRecipesByCategoryId(categoryId);
    }
}

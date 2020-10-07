package com.miti.server.service.impl;

import com.miti.server.model.dto.RecipeDTO;
import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import com.miti.server.model.form.RecipeForm;
import com.miti.server.repository.CategoryRepository;
import com.miti.server.repository.RecipeRepository;
import com.miti.server.repository.UserRepository;
import com.miti.server.service.CategoryService;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe addRecipe(RecipeDTO recipeDTO) {
        return addRecipe(new Recipe(recipeDTO));
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(()
                -> new RuntimeException("Recipe with id: " + recipeId + " doesnt exist!"));
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> getRecipesByAuthor(User author) {;
        return recipeRepository.getRecipesByAuthor(author);
    }

    @Override
    public List<Recipe> getRecipesByAuthorId(Long id) {
        User _user = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User with id: " + id + " doesn't exist!"));
        return recipeRepository.getRecipesByAuthor(_user);
    }

    @Override
    public List<Recipe> getRecipesByCategoryId(String categoryId) {
        return recipeRepository.getRecipesByCategoryId(categoryId);
    }

    @Override
    public boolean checkFieldsExist(Long userId, String categoryId) {
        User _user = userService.getUserById(userId);
        Category _category = categoryService.getCategoryById(categoryId);

        return _user != null && _category != null;
    }

    @Override
    public Recipe addRecipeDTO(RecipeForm recipeForm) {
        String _name = recipeForm.getName();
        String _description = recipeForm.getDescription();
        User _author = userService.getUserById(recipeForm.getAuthorId());
        Category _category = categoryService.getCategoryById(recipeForm.getCategoryId());

        RecipeDTO recipeDTO = new RecipeDTO(_name, _description, _author, _category);
        return addRecipe(recipeDTO);
    }
}

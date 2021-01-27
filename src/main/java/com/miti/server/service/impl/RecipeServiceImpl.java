package com.miti.server.service.impl;

import com.miti.server.enums.Kitchen;
import com.miti.server.model.entity.Comment;
import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.model.entity.Recipe;
import com.miti.server.repository.CommentRepository;
import com.miti.server.repository.ContextIngredientRepository;
import com.miti.server.repository.RecipeRepository;
import com.miti.server.service.*;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeServiceImpl implements RecipeService {
  private final RecipeRepository recipeRepository;
  private final CommentRepository commentRepository;
  private final ContextIngredientRepository contextIngredientRepository;

  private final UserService userService;
  private final CategoryService categoryService;

  @Override
  public Recipe addRecipe(Recipe recipe) {
    if (existsByName(recipe.getName()))
      return recipeRepository.save(new Recipe(
          recipe.getName(),
          recipe.getDescription(),
          userService.getUserById(recipe.getAuthor().getId()),
          categoryService.getCategoryById(recipe.getCategory().getId()),
          recipe.getKitchen(),
          recipe.getTime()
      ));
    throw new RuntimeException("Recipe with name: " + recipe.getName() + " already exists!");
  }

  @Override
  public void addAllRecipes(List<Recipe> recipes) {
    List<Recipe> _recipes = new ArrayList<>();
    for (Recipe recipe : recipes) {
      if (existsByName(recipe.getName()))
        _recipes.add(recipe);
    }
    recipeRepository.saveAll(_recipes);
  }

  @Override
  public Recipe editRecipe(Long recipeId, Recipe newRecipe) {
    if (existsByName(newRecipe.getName())) {
      return recipeRepository.findById(recipeId).map(recipe -> {
        recipe.setName(newRecipe.getName());
        recipe.setDescription(newRecipe.getDescription());
        recipe.setCategory(categoryService.getCategoryById(newRecipe.getCategory().getId()));
        return recipeRepository.save(recipe);
      }).orElseThrow(() -> new RuntimeException("Recipe with id: " + recipeId + " doesn't exist!"));
    } throw new RuntimeException("Recipe with name: " + newRecipe.getName() + " already exist!");
  }

  @Override
  public Recipe getRecipeById(Long recipeId) {
    return recipeRepository.findById(recipeId).orElseThrow(()
        -> new RuntimeException("Recipe with id: " + recipeId + " doesn't exist!"));
  }

  @Override
  public Recipe getRecipeByName(String name) {
    if (Check.param(name)) {
      Recipe recipe = recipeRepository.getRecipeByName(name);
      if (recipe != null)
        return recipe;
      throw new RuntimeException("Recipe with name: " + name + " doesn't exist!");
    }
    throw new RuntimeException("Name: " + name + " is incorrect!");
  }

  @Override
  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  }

  @Override
  public List<Recipe> getRecipesByAuthorId(Long authorId) {
    if (Check.param(authorId)) {
      List<Recipe> recipes = recipeRepository.getRecipesByAuthor(userService.getUserById(authorId));
      if (recipes != null)
        return recipes;
      throw new RuntimeException("Recipe with authorId: " + authorId + " doesn't exist!");
    }
    throw new RuntimeException("AuthorId: " + authorId + " is incorrect!");
  }

  @Override
  public List<Recipe> getRecipesByCategoryId(String categoryId) {
    if (Check.param(categoryId)) {
      List<Recipe> recipes = recipeRepository.getRecipesByCategory(categoryService.getCategoryById(categoryId));
      if (recipes != null)
        return recipes;
      throw new RuntimeException("Recipe with categoryId: " + categoryId + " doesn't exist!");
    }
    throw new RuntimeException("CategoryId: " + categoryId + " is incorrect!");
  }

  @Override
  public List<Recipe> getRecipesByKitchen(String kitchenName) {
    if (Check.param(kitchenName)) {
      Kitchen kitchen = Kitchen.valueOf(kitchenName);
      List<Recipe> recipes = recipeRepository.getRecipesByKitchen(kitchen);
      if (recipes != null)
        return recipes;
      throw new RuntimeException("Recipe with kitchen: " + kitchen + " doesn't exist!");
    }
    throw new RuntimeException("Kitchen: " + kitchenName + " is incorrect!");
  }

  @Override
  public List<Recipe> getRecipesByTimeLessThanEqual(int time) {
    return recipeRepository.getRecipesByTimeLessThanEqual(time);
  }

  @Override
  public void deleteRecipeById(Long recipeId) {
    Recipe recipe = getRecipeById(recipeId);
    List<Comment> comments = recipe.getCommentList();
    List<ContextIngredient> contextIngredients = recipe.getContextIngredientList();
    for (Comment comment : comments)
      deleteCommentById(comment.getId());
    for (ContextIngredient contextIngredient : contextIngredients)
      deleteIngredientContextById(contextIngredient.getId());
    recipeRepository.deleteById(recipeId);
  }

  @Override
  public void deleteCommentById(Long commentId) {
    commentRepository.deleteById(commentId);
  }

  @Override
  public void deleteIngredientContextById(Long ingredientContextId) {
    contextIngredientRepository.deleteById(ingredientContextId);
  }

  private boolean existsByName(String name) {
    return !recipeRepository.existsByName(name);
  }
}

package com.miti.server.service.impl;

import com.miti.data.enums.Kitchen;
import com.miti.data.mapper.RecipeMapper;
import com.miti.data.model.CalorieContent;
import com.miti.data.model.Recipe;
import com.miti.data.repository.CommentRepository;
import com.miti.data.repository.ContextIngredientRepository;
import com.miti.data.repository.RatingRepository;
import com.miti.data.repository.RecipeRepository;
import com.miti.server.api.request.RecipeRequest;
import com.miti.server.api.response.RecipeResponse;
import com.miti.server.service.CategoryService;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;
  private final CommentRepository commentRepository;
  private final ContextIngredientRepository contextIngredientRepository;
  private final RatingRepository ratingRepository;

  private final UserService userService;
  private final CategoryService categoryService;

  private final RecipeMapper recipeMapper;

  @Override
  public RecipeResponse addRecipe(RecipeRequest recipeRequest) {
    Recipe recipe = recipeMapper.recipeRequestToRecipeModel(recipeRequest);
    recipeRepository.save(recipe);

    return recipeMapper.recipeModelToRecipeResponse(recipe);
  }

  @Override
  public void addAllRecipes(List<Recipe> recipes) {
    recipeRepository.saveAll(recipes);
  }

  @Override
  public Recipe editRecipe(Recipe newRecipe) {
    return recipeRepository.findById(newRecipe.getId()).map(recipe -> {
      recipe.setName(newRecipe.getName());
      recipe.setDescription(newRecipe.getDescription());
      recipe.setCategory(categoryService.getCategoryById(newRecipe.getCategory().getId()));

      return recipeRepository.save(recipe); }).orElseThrow(()
        -> new RuntimeException("Recipe with id: " + newRecipe.getId() + " doesn't exist!"));
  }

  @Override
  public Recipe getRecipeById(long recipeId) {
    return recipeRepository.findById(recipeId).orElseThrow(()
        -> new RuntimeException("Recipe with id: " + recipeId + " doesn't exist!"));
  }

  @Override
  public List<Recipe> getRecipeByName(String name) {
    if (!StringUtils.isEmpty(name)) {
      List<Recipe> recipes = recipeRepository.getRecipesByName(name);
      if (recipes != null) {
        return recipes;
      }
      throw new RuntimeException("Recipe with name: " + name + " doesn't exist!");
    }
    throw new RuntimeException("Name: " + name + " is incorrect!");
  }

  @Override
  public Recipe getRecipeByCalorie(CalorieContent calorieContent) {
    return recipeRepository.getRecipeByCalorie(calorieContent);
  }

  @Override
  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  }

  @Override
  public List<Recipe> getRecipesByAuthorId(long authorId) {
      List<Recipe> recipes = recipeRepository.getRecipesByAuthor(userService.getUserById(authorId));
      if (recipes != null) {
        return recipes;
      }
      throw new RuntimeException("Recipe with authorId: " + authorId + " doesn't exist!");
  }

  @Override
  public List<Recipe> getRecipesByCategoryId(String categoryId) {
    if (!StringUtils.isEmpty(categoryId)) {
      List<Recipe> recipes = recipeRepository
          .getRecipesByCategory(categoryService.getCategoryById(categoryId));
      if (recipes != null) {
        return recipes;
      }
      throw new RuntimeException("Recipe with categoryId: " + categoryId + " doesn't exist!");
    }
    else {
      throw new RuntimeException("CategoryId: " + categoryId + " is incorrect!");
    }
  }

  @Override
  public List<Recipe> getRecipesByKitchen(String kitchenName) {
    if (!StringUtils.isEmpty(kitchenName)) {
      Kitchen kitchen = Kitchen.valueOf(kitchenName);
      List<Recipe> recipes = recipeRepository.getRecipesByKitchen(kitchen);
      if (recipes != null) {
        return recipes;
      }
      throw new RuntimeException("Recipe with kitchen: " + kitchen + " doesn't exist!");
    }
    else {
      throw new RuntimeException("Kitchen: " + kitchenName + " is incorrect!");
    }
  }

  @Override
  public List<Recipe> getRecipesByTimeLessThanEqual(int time) {
    return recipeRepository.getRecipesByTimeLessThanEqual(time);
  }

  @Override
  public List<Recipe> getRecipesByCreateDateBetween(LocalDateTime recipePublicationDateStart) {
    return recipeRepository.getRecipesByCreateDateBetween(recipePublicationDateStart, LocalDateTime.now());
  }

  @Override
  public List<Recipe> getFavouriteRecipesByUserId(Long userId) {
    return recipeRepository.getRecipesByFavouriteUsers(userService.getUserById(userId));
  }

  @Override
  public void deleteRecipeById(Long recipeId) {
    Recipe recipe = getRecipeById(recipeId);

    recipe.getCommentList().forEach(comment ->
        commentRepository.deleteById(comment.getId()));

    recipe.getRating().forEach(rating ->
        ratingRepository.deleteById(rating.getId()));

    recipe.getContextIngredientList().forEach(contextIngredient ->
        contextIngredientRepository.deleteById(contextIngredient.getId()));

    recipeRepository.deleteById(recipeId);
  }
}

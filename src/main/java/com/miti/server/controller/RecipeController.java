package com.miti.server.controller;

import com.miti.server.model.entity.Recipe;
import com.miti.server.api.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/recipe")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeController {

  private final RecipeService recipeService;

  @PostMapping("addRecipe")
  public Recipe addRecipe(@RequestBody Recipe recipe) {
    return recipeService.addRecipe(recipe);
  }

  @PutMapping("editRecipe")
  public Recipe editRecipe(@RequestParam(name = "recipeId") Long recipeId,
      @RequestBody Recipe recipe) {
    return recipeService.editRecipe(recipeId, recipe);
  }

  @GetMapping("getRecipeById")
  public Recipe getRecipeById(@RequestParam(name = "recipeId") Long recipeId) {
    return recipeService.getRecipeById(recipeId);
  }

  @GetMapping("getRecipeByName")
  public Recipe getRecipeByName(@RequestParam(name = "name") String name) {
    return recipeService.getRecipeByName(name);
  }

  @GetMapping("getAllRecipes")
  public List<Recipe> getAllRecipes() {
    return recipeService.getAllRecipes();
  }

  @GetMapping("getRecipesByAuthorId")
  public List<Recipe> getRecipesByAuthorId(@RequestParam(name = "userId") Long authorId) {
    return recipeService.getRecipesByAuthorId(authorId);
  }

  @GetMapping("getRecipesByCategoryId")
  public List<Recipe> getRecipesByCategoryId(@RequestParam(name = "categoryId") String categoryId) {
    return recipeService.getRecipesByCategoryId(categoryId);
  }

  @GetMapping("getRecipesByKitchen")
  public List<Recipe> getRecipesByKitchen(@RequestParam(name = "kitchen") String kitchenName) {
    return recipeService.getRecipesByKitchen(kitchenName);
  }

  @DeleteMapping("deleteRecipeById")
  public String deleteRecipeById(@RequestParam(name = "recipeId") Long recipeId) {
    recipeService.deleteRecipeById(recipeId);
    return "Done!";
  }
}

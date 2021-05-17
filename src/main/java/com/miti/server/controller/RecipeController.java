package com.miti.server.controller;

import com.miti.data.model.Recipe;
import com.miti.server.api.request.RecipeRequest;
import com.miti.server.api.response.RecipeResponse;
import com.miti.server.service.RecipeService;
import com.miti.server.util.SearchFilter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/recipe")
@AllArgsConstructor
public class RecipeController {

  private final RecipeService recipeService;

  private final SearchFilter searchFilter;

  @PostMapping("")
  public RecipeResponse addRecipe(@RequestBody RecipeRequest recipe) {
    return recipeService.addRecipe(recipe);
  }

  @PatchMapping("/{id}")
  public Recipe editRecipe(@RequestBody Recipe recipe) {
    return recipeService.editRecipe(recipe);
  }

  @GetMapping("/{id}")
  public Recipe getRecipeById(@PathVariable long id) {
    return recipeService.getRecipeById(id);
  }

  @GetMapping("")
  public List<Recipe> getAllRecipes() {
    return recipeService.getAllRecipes();
  }

  @GetMapping("/getByName/{name}")
  public List<Recipe> getRecipesByName(@PathVariable String name) {
    return recipeService.getRecipeByName(name);
  }

  @GetMapping("/getByAuthorId/{id}")
  public List<Recipe> getRecipesByAuthorId(@PathVariable long id) {
    return recipeService.getRecipesByAuthorId(id);
  }

  @GetMapping("/getByCategoryId/{id}")
  public List<Recipe> getRecipesByCategoryId(@PathVariable String id) {
    return recipeService.getRecipesByCategoryId(id);
  }

  @GetMapping("/getByKitchen/{kitchen}")
  public List<Recipe> getRecipesByKitchen(@PathVariable String kitchen) {
    return recipeService.getRecipesByKitchen(kitchen);
  }

  @GetMapping(value = "/getFavourites/{userId}")
  public List<Recipe> getFavouriteRecipesByUserId(@PathVariable Long userId) {
    return recipeService.getFavouriteRecipesByUserId(userId);
  }

  @DeleteMapping("/{id}")
  public String deleteRecipeById(@PathVariable Long id) {
    recipeService.deleteRecipeById(id);

    return "Successfully removed RECIPE with id [" + id + "]";
  }

  @GetMapping(path = "/kitchen")
  public List<String> getAllKitchenFromServer() {
    return searchFilter.getAllKitchen();
  }
}

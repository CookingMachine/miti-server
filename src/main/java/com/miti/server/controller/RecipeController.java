package com.miti.server.controller;

import com.miti.server.api.RecipeService;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.request.RecipeRequest;
import com.miti.server.model.response.RecipeResponse;
import com.miti.server.util.SearchFilter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/recipe")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
  public Recipe getRecipeById(@PathVariable Long id) {
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
  public List<Recipe> getRecipesByAuthorId(@PathVariable Long id) {
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

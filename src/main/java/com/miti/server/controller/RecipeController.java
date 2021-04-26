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

  @PostMapping("")
  public Recipe addRecipe(@RequestBody Recipe recipe) {
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

  @DeleteMapping("/{id}")
  public String deleteRecipeById(@PathVariable Long id) {
    recipeService.deleteRecipeById(id);
    return "Done!";
  }
}

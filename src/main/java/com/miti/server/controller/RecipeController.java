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

  @PutMapping("/{id}")
  public Recipe editRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
    return recipeService.editRecipe(id, recipe);
  }

  @GetMapping("/{id}")
  public Recipe getRecipeById(@PathVariable Long id) {
    return recipeService.getRecipeById(id);
  }

  @GetMapping("")
  public List<Recipe> getAllRecipes() {
    return recipeService.getAllRecipes();
  }

  @GetMapping("/getRecipesByName/{name}")
  public List<Recipe> getRecipeByName(@PathVariable String name) {
    return recipeService.getRecipeByName(name);
  }

  @GetMapping("/getRecipesByAuthorId/{id}")
  public List<Recipe> getRecipesByAuthorId(@PathVariable Long id) {
    return recipeService.getRecipesByAuthorId(id);
  }

  @GetMapping("/getRecipesByCategoryId/{id}")
  public List<Recipe> getRecipesByCategoryId(@PathVariable String id) {
    return recipeService.getRecipesByCategoryId(id);
  }

  @GetMapping("/getRecipesByKitchen/{kitchen}")
  public List<Recipe> getRecipesByKitchen(@PathVariable String kitchen) {
    return recipeService.getRecipesByKitchen(kitchen);
  }

  @DeleteMapping("/{id}")
  public String deleteRecipeById(@PathVariable Long id) {
    recipeService.deleteRecipeById(id);
    return "Done!";
  }
}

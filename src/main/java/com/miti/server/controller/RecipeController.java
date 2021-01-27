package com.miti.server.controller;

import com.miti.server.model.entity.Recipe;
import com.miti.server.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeController {
  private final RecipeService recipeService;
  @PostMapping("/recipe/addRecipe")
  public Recipe addRecipe(@RequestBody Recipe recipe) {
    return recipeService.addRecipe(recipe);
  }

  @PutMapping("/recipe/editRecipe")
  public Recipe editRecipe(@RequestParam(name = "id") Long recipeId, @RequestBody Recipe recipe) {
    return  recipeService.editRecipe(recipeId ,recipe);
  }

  @GetMapping("/recipe/getRecipeById")
  public Recipe getRecipeById(@RequestParam(name = "id") Long recipeId) {
    return recipeService.getRecipeById(recipeId);
  }

  @GetMapping("/recipe/getRecipeByName")
  public Recipe getRecipeByName(@RequestParam(name = "name") String name) {
    return recipeService.getRecipeByName(name);
  }

  @GetMapping("/recipe/getAllRecipes")
  public List<Recipe> getAllRecipes() {
    return recipeService.getAllRecipes();
  }

  @GetMapping("/recipe/getRecipesByAuthorId")
  public List<Recipe> getRecipesByAuthorId(@RequestParam(name = "userId") Long authorId) {
    return recipeService.getRecipesByAuthorId(authorId);
  }

  @GetMapping("/recipe/getRecipesByCategoryId")
  public List<Recipe> getRecipesByCategoryId(@RequestParam(name = "categoryId") String categoryId) {
    return recipeService.getRecipesByCategoryId(categoryId);
  }

  @GetMapping("/recipe/getRecipesByKitchen")
  public List<Recipe> getRecipesByKitchen(@RequestParam(name = "kitchen") String kitchenName) {
    return recipeService.getRecipesByKitchen(kitchenName);
  }

  @DeleteMapping("/recipe/deleteRecipeById")
  public String deleteRecipeById(@RequestParam(name = "id") Long recipeId) {
    recipeService.deleteRecipeById(recipeId);
    return "Done!";
  }
}

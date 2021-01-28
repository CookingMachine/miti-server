package com.miti.server.controller;

import com.miti.server.model.entity.Ingredient;
import com.miti.server.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IngredientController {
  private final IngredientService ingredientService;

  @PostMapping("/ingredient/addIngredient")
  public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
    return ingredientService.addIngredient(ingredient);
  }

  @GetMapping("/ingredient/getIngredientById")
  public Ingredient getIngredientById(@RequestParam(name = "ingredientId") String ingredientId) {
    return ingredientService.getIngredientById(ingredientId);
  }

  @GetMapping("/ingredient/getIngredientByName")
  public Ingredient getIngredientByName(@RequestParam(name = "name") String name) {
    return ingredientService.getIngredientByName(name);
  }

  @GetMapping("/ingredient/getAllIngredients")
  public List<Ingredient> getAllIngredients() {
    return ingredientService.getAllIngredients();
  }

  @GetMapping("/ingredient/getIngredientsByCategory")
  public List<Ingredient> getIngredientsByCategory(@RequestParam(name = "categoryName") String categoryName) {
    return ingredientService.getIngredientsByCategory(categoryName);
  }

  @DeleteMapping("/ingredient/deleteIngredientById")
  public String deleteIngredientById(@RequestParam(name = "ingredientId") String ingredientId) {
    ingredientService.deleteIngredientById(ingredientId);
    return "Done!";
  }
}

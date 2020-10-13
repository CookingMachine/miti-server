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

  @PostMapping("/addIngredient")
  public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
    return ingredientService.addIngredient(ingredient);
  }

  @GetMapping("/getIngredientById")
  public Ingredient getIngredientById(@RequestParam String ingredientId) {
    return ingredientService.getIngredientById(ingredientId);
  }

  @GetMapping("/getIngredientByName")
  public Ingredient getIngredientByName(@RequestParam String name) {
    return ingredientService.getIngredientByName(name);
  }

  @GetMapping("/getAllIngredients")
  public List<Ingredient> getAllIngredients() {
    return ingredientService.getAllIngredients();
  }

  @GetMapping("/getIngredientsByCategory")
  public List<Ingredient> getIngredientsByCategory(@RequestParam String categoryName) {
    return ingredientService.getIngredientsByCategory(categoryName);
  }

  @DeleteMapping("/deleteIngredientById")
  public String deleteIngredientById(@RequestParam String ingredientId) {
    ingredientService.deleteIngredientById(ingredientId);
    return "Done!";
  }
}

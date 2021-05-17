package com.miti.server.controller;

import com.miti.data.model.Ingredient;
import com.miti.server.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ingredient")
@AllArgsConstructor
public class IngredientController {

  private final IngredientService ingredientService;

  @PostMapping("")
  public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
    return ingredientService.addIngredient(ingredient);
  }

  @PatchMapping("/{id}")
  public Ingredient editIngredient(@RequestBody Ingredient ingredient) {
    return ingredientService.editIngredient(ingredient);
  }

  @GetMapping("/{id}")
  public Ingredient getIngredientById(@PathVariable String id) {
    return ingredientService.getIngredientById(id);
  }

  @GetMapping("/getByName/{name}")
  public Ingredient getIngredientByName(@PathVariable String name) {
    return ingredientService.getIngredientByName(name);
  }

  @GetMapping("")
  public List<Ingredient> getAllIngredients() {
    return ingredientService.getAllIngredients();
  }

  @GetMapping("/getByCategory/{category}")
  public List<Ingredient> getIngredientsByCategory(
      @PathVariable String category) {
    return ingredientService.getIngredientsByCategory(category);
  }

  @DeleteMapping("/{id}")
  public String deleteIngredientById(@PathVariable String id) {
    ingredientService.deleteIngredientById(id);

    return "Successfully removed INGREDIENT with id [" + id + "]";
  }
}

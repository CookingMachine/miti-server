package com.miti.server.controller;

import com.miti.server.model.entity.IngredientContext;
import com.miti.server.service.IngredientContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IngredientContextController {
  private final IngredientContextService ingredientContextService;

  @PostMapping("/addIngredientContext")
  public IngredientContext addIngredientContext(@RequestBody IngredientContext ingredientContext) {
    return ingredientContextService.addIngredientContext(ingredientContext);
  }

  @GetMapping("/getIngredientContextById")
  public IngredientContext getIngredientContextById(@RequestParam Long ingredientContextId) {
    return ingredientContextService.getIngredientContextById(ingredientContextId);
  }

  @GetMapping("/getAllIngredientContexts")
  public List<IngredientContext> getAllIngredientContexts() {
    return ingredientContextService.getAllIngredientContexts();
  }

  @GetMapping("/getIngredientContextsByAmountLessThan")
  public List<IngredientContext> getIngredientContextByAmountLessThan(@RequestParam Integer amount) {
    return ingredientContextService.getIngredientContextsByAmountLessThan(amount);
  }

  @GetMapping("/getIngredientContextsByAmountGreaterThan")
  public List<IngredientContext> getIngredientContextByAmountGreaterThan(@RequestParam Integer amount) {
    return ingredientContextService.getIngredientContextByAmountGreaterThan(amount);
  }

  @GetMapping("/getIngredientContextsByMeasure")
  public List<IngredientContext> getIngredientContextsByMeasure(@RequestParam String measureName) {
    return ingredientContextService.getIngredientContextsByMeasure(measureName);
  }

  @GetMapping("/getIngredientContextsByIngredientId")
  public List<IngredientContext> getIngredientContextsByIngredientId(@RequestParam String ingredientId) {
    return ingredientContextService.getIngredientContextsByIngredientId(ingredientId);
  }

  @GetMapping("/getIngredientContextsByRecipeId")
  public List<IngredientContext> getIngredientContextsByRecipeId(@RequestParam Long recipeId) {
    return ingredientContextService.getIngredientContextsByRecipeId(recipeId);
  }

  @DeleteMapping("/deleteIngredientContextById")
  public String deleteIngredientContextById(@RequestParam Long ingredientContextId) {
    ingredientContextService.deleteIngredientContextById(ingredientContextId);
    return "Done!";
  }
}

package com.miti.server.controller;

import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.service.ContextIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IngredientContextController {
  private final ContextIngredientService contextIngredientService;

  @PostMapping("/addContextIngredient")
  public ContextIngredient addIngredientContext(@RequestBody ContextIngredient contextIngredient) {
    return contextIngredientService.addContextIngredient(contextIngredient);
  }

  @GetMapping("/getContextIngredientsById")
  public ContextIngredient getIngredientContextById(@RequestParam Long ingredientContextId) {
    return contextIngredientService.getContextIngredientById(ingredientContextId);
  }

  @GetMapping("/getAllContextIngredients")
  public List<ContextIngredient> getAllIngredientContexts() {
    return contextIngredientService.getAllContextIngredients();
  }

  @GetMapping("/getContextIngredientsByAmountLessThan")
  public List<ContextIngredient> getIngredientContextByAmountLessThan(@RequestParam Long amount) {
    return contextIngredientService.getContextIngredientsByAmountLessThan(amount);
  }

  @GetMapping("/getContextIngredientsByAmountGreaterThan")
  public List<ContextIngredient> getIngredientContextByAmountGreaterThan(@RequestParam Long amount) {
    return contextIngredientService.getContextIngredientsByAmountGreaterThan(amount);
  }

  @GetMapping("/getContextIngredientsByMeasure")
  public List<ContextIngredient> getIngredientContextsByMeasure(@RequestParam String measureName) {
    return contextIngredientService.getContextIngredientsByMeasure(measureName);
  }

  @GetMapping("/getContextIngredientsByIngredientId")
  public List<ContextIngredient> getIngredientContextsByIngredientId(@RequestParam String ingredientId) {
    return contextIngredientService.getContextIngredientsByIngredientId(ingredientId);
  }

  @GetMapping("/getContextIngredientsByRecipeId")
  public List<ContextIngredient> getIngredientContextsByRecipeId(@RequestParam Long recipeId) {
    return contextIngredientService.getContextIngredientsByRecipeId(recipeId);
  }

  @DeleteMapping("/deleteContextIngredientById")
  public String deleteIngredientContextById(@RequestParam Long ingredientContextId) {
    contextIngredientService.deleteContextIngredientById(ingredientContextId);
    return "Done!";
  }
}

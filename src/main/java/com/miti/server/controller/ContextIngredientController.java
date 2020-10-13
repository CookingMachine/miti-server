package com.miti.server.controller;

import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.service.ContextIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContextIngredientController {
  private final ContextIngredientService contextIngredientService;

  @PostMapping("/addContextIngredient")
  public ContextIngredient addContextIngredient(@RequestBody ContextIngredient contextIngredient) {
    return contextIngredientService.addContextIngredient(contextIngredient);
  }

  @GetMapping("/getContextIngredientById")
  public ContextIngredient getContextIngredientById(@RequestParam Long contextIngredientId) {
    return contextIngredientService.getContextIngredientById(contextIngredientId);
  }

  @GetMapping("/getAllContextIngredients")
  public List<ContextIngredient> getAllContextIngredients() {
    return contextIngredientService.getAllContextIngredients();
  }

  @GetMapping("/getContextIngredientsByAmountLessThan")
  public List<ContextIngredient> getContextIngredientsByAmountLessThan(@RequestParam Long amount) {
    return contextIngredientService.getContextIngredientsByAmountLessThan(amount);
  }

  @GetMapping("/getContextIngredientsByAmountGreaterThan")
  public List<ContextIngredient> getContextIngredientsByAmountGreaterThan(@RequestParam Long amount) {
    return contextIngredientService.getContextIngredientsByAmountGreaterThan(amount);
  }

  @GetMapping("/getContextIngredientsByMeasure")
  public List<ContextIngredient> getContextIngredientsByMeasure(@RequestParam String measureName) {
    return contextIngredientService.getContextIngredientsByMeasure(measureName);
  }

  @GetMapping("/getContextIngredientsByIngredientId")
  public List<ContextIngredient> getContextIngredientsByIngredientId(@RequestParam String ingredientId) {
    return contextIngredientService.getContextIngredientsByIngredientId(ingredientId);
  }

  @GetMapping("/getContextIngredientsByRecipeId")
  public List<ContextIngredient> getContextIngredientsByRecipeId(@RequestParam Long recipeId) {
    return contextIngredientService.getContextIngredientsByRecipeId(recipeId);
  }

  @DeleteMapping("/deleteContextIngredientById")
  public String deleteContextIngredientById(@RequestParam Long contextIngredientId) {
    contextIngredientService.deleteContextIngredientById(contextIngredientId);
    return "Done!";
  }
}

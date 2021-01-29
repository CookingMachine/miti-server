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

  @PostMapping("/contextIngredient/addContextIngredient")
  public ContextIngredient addContextIngredient(@RequestBody ContextIngredient contextIngredient) {
    return contextIngredientService.addContextIngredient(contextIngredient);
  }

  @GetMapping("/contextIngredient/getContextIngredientById")
  public ContextIngredient getContextIngredientById(@RequestParam(name = "contextIngredientId") Long contextIngredientId) {
    return contextIngredientService.getContextIngredientById(contextIngredientId);
  }

  @GetMapping("/contextIngredient/getAllContextIngredients")
  public List<ContextIngredient> getAllContextIngredients() {
    return contextIngredientService.getAllContextIngredients();
  }

  @GetMapping("/contextIngredient/getContextIngredientsByAmountLessThan")
  public List<ContextIngredient> getContextIngredientsByAmountLessThan(@RequestParam(name = "amount") Long amount) {
    return contextIngredientService.getContextIngredientsByAmountLessThan(amount);
  }

  @GetMapping("/contextIngredient/getContextIngredientsByAmountGreaterThan")
  public List<ContextIngredient> getContextIngredientsByAmountGreaterThan(@RequestParam(name = "amount") Long amount) {
    return contextIngredientService.getContextIngredientsByAmountGreaterThan(amount);
  }

  @GetMapping("/contextIngredient/getContextIngredientsByMeasure")
  public List<ContextIngredient> getContextIngredientsByMeasure(@RequestParam(name = "measure") String measureName) {
    return contextIngredientService.getContextIngredientsByMeasure(measureName);
  }

  @GetMapping("/contextIngredient/getContextIngredientsByIngredientId")
  public List<ContextIngredient> getContextIngredientsByIngredientId(@RequestParam(name = "ingredientId") String ingredientId) {
    return contextIngredientService.getContextIngredientsByIngredientId(ingredientId);
  }

  @GetMapping("/contextIngredient/getContextIngredientsByRecipeId")
  public List<ContextIngredient> getContextIngredientsByRecipeId(@RequestParam(name = "recipeId") Long recipeId) {
    return contextIngredientService.getContextIngredientsByRecipeId(recipeId);
  }

  @DeleteMapping("/contextIngredient/deleteContextIngredientById")
  public String deleteContextIngredientById(@RequestParam(name = "contextIngredientId") Long contextIngredientId) {
    contextIngredientService.deleteContextIngredientById(contextIngredientId);
    return "Done!";
  }
}

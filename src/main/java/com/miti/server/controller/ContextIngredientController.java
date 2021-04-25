package com.miti.server.controller;

import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.api.ContextIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/contextIngredient")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContextIngredientController {

  private final ContextIngredientService contextIngredientService;

  @PostMapping("")
  public ContextIngredient addContextIngredient(@RequestBody ContextIngredient contextIngredient) {
    return contextIngredientService.addContextIngredient(contextIngredient);
  }

  @GetMapping("/{id}")
  public ContextIngredient getContextIngredientById(@PathVariable Long id) {
    return contextIngredientService.getContextIngredientById(id);
  }

  @GetMapping("")
  public List<ContextIngredient> getAllContextIngredients() {
    return contextIngredientService.getAllContextIngredients();
  }

  @GetMapping("/getContextIngredientsByAmountLessThan")
  public List<ContextIngredient> getContextIngredientsByAmountLessThan(
      @RequestParam(name = "amount") Long amount) {
    return contextIngredientService.getContextIngredientsByAmountLessThan(amount);
  }

  @GetMapping("/getContextIngredientsByAmountGreaterThan")
  public List<ContextIngredient> getContextIngredientsByAmountGreaterThan(
      @RequestParam(name = "amount") Long amount) {
    return contextIngredientService.getContextIngredientsByAmountGreaterThan(amount);
  }

  @GetMapping("/getContextIngredientsByMeasure/{measure}")
  public List<ContextIngredient> getContextIngredientsByMeasure(@PathVariable String measure) {
    return contextIngredientService.getContextIngredientsByMeasure(measure);
  }

  @GetMapping("/getContextIngredientsByIngredientId/{id}")
  public List<ContextIngredient> getContextIngredientsByIngredientId(@PathVariable String id) {
    return contextIngredientService.getContextIngredientsByIngredientId(id);
  }

  @GetMapping("/getContextIngredientsByRecipeId/{id}")
  public List<ContextIngredient> getContextIngredientsByRecipeId(@PathVariable Long id) {
    return contextIngredientService.getContextIngredientsByRecipeId(id);
  }

  @DeleteMapping("/{id}")
  public String deleteContextIngredientById(@PathVariable Long id) {
    contextIngredientService.deleteContextIngredientById(id);
    return "Done!";
  }
}

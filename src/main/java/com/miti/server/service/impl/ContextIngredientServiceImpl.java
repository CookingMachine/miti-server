package com.miti.server.service.impl;

import com.miti.data.enums.Measure;
import com.miti.data.model.ContextIngredient;
import com.miti.data.repository.ContextIngredientRepository;
import com.miti.server.service.ContextIngredientService;
import com.miti.server.service.IngredientService;
import com.miti.server.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ContextIngredientServiceImpl implements ContextIngredientService {

  private final ContextIngredientRepository contextIngredientRepository;
  private final RecipeService recipeService;
  private final IngredientService ingredientService;

  @Override
  public ContextIngredient addContextIngredient(ContextIngredient contextIngredient) {
    if (existsByIngredientIdAndRecipeId(
        contextIngredient.getIngredient().getId(),
        contextIngredient.getRecipe().getId()
    )) {
      return contextIngredientRepository.save(new ContextIngredient(
          contextIngredient.getAmount(),
          contextIngredient.getMeasure(),
          ingredientService.getIngredientById(contextIngredient.getIngredient().getId()),
          recipeService.getRecipeById(contextIngredient.getRecipe().getId())
      ));
    }
    throw new RuntimeException("In recipe with id: " + contextIngredient.getRecipe().getId() + " " +
        "already exist ingredient with id: " + contextIngredient.getIngredient().getId());
  }

  @Override
  public void addAllContextIngredients(List<ContextIngredient> contextIngredients) {
    List<ContextIngredient> _ContextIngredients = new ArrayList<>();
    for (ContextIngredient contextIngredient : contextIngredients) {
      if (existsByIngredientIdAndRecipeId(
          contextIngredient.getIngredient().getId(),
          contextIngredient.getRecipe().getId()
      )) {
        _ContextIngredients.add(contextIngredient);
      }
    }
    contextIngredientRepository.saveAll(_ContextIngredients);
  }

  @Override
  public ContextIngredient getContextIngredientById(long contextIngredientId) {
    return contextIngredientRepository.findById(contextIngredientId).orElseThrow(()
        -> new RuntimeException(
        "ContextIngredient with id: " + contextIngredientId + " doesn't exist!"));
  }

  @Override
  public List<ContextIngredient> getAllContextIngredients() {
    return (List<ContextIngredient>) contextIngredientRepository.findAll();
  }

  @Override
  public List<ContextIngredient> getContextIngredientsByAmountLessThan(long amount) {
      List<ContextIngredient> contextIngredients = contextIngredientRepository.
          getContextIngredientByAmountLessThan(amount);
      if (contextIngredients != null) {
        return contextIngredients;
      }
      throw new RuntimeException("Nothing is less than: " + amount);
  }

  @Override
  public List<ContextIngredient> getContextIngredientsByAmountGreaterThan(long amount) {
      List<ContextIngredient> contextIngredients = contextIngredientRepository.
          getContextIngredientByAmountGreaterThan(amount);
      if (contextIngredients != null) {
        return contextIngredients;
      }
      throw new RuntimeException("Nothing is greater than: " + amount);
  }

  @Override
  public List<ContextIngredient> getContextIngredientsByMeasure(String measureName) {
    if (!StringUtils.isEmpty(measureName)) {
      Measure measure = Measure.valueOf(measureName);
      List<ContextIngredient> contextIngredients = contextIngredientRepository.
          getContextIngredientsByMeasure(measure);
      if (contextIngredients != null) {
        return contextIngredients;
      }
      throw new RuntimeException(
          "ContextIngredient with measure: " + measureName + " don't exist!");
    }
    throw new RuntimeException("MeasureName: " + measureName + " is incorrect!");

  }

  @Override
  public List<ContextIngredient> getContextIngredientsByIngredientId(String ingredientId) {
    if (!StringUtils.isEmpty(ingredientId)) {
      List<ContextIngredient> contextIngredients = contextIngredientRepository.
          getContextIngredientsByIngredient(ingredientService.getIngredientById(ingredientId));
      if (contextIngredients != null) {
        return contextIngredients;
      }
      throw new RuntimeException(
          "ContextIngredient with ingredientId: " + ingredientId + " don't exist!");
    }
    throw new RuntimeException("IngredientId: " + ingredientId + " is incorrect!");
  }

  @Override
  public List<ContextIngredient> getContextIngredientsByRecipeId(long recipeId) {
      List<ContextIngredient> contextIngredients = contextIngredientRepository.
          getContextIngredientsByRecipe(recipeService.getRecipeById(recipeId));
      if (contextIngredients != null) {
        return contextIngredients;
      }
      throw new RuntimeException("ContextIngredient with recipeId: " + recipeId + " don't exist!");
  }

  @Override
  public void deleteContextIngredientById(long contextIngredientId) {
    contextIngredientRepository.deleteById(contextIngredientId);
  }

  private boolean existsByIngredientIdAndRecipeId(String ingredientId, long recipeId) {
    return !contextIngredientRepository.existsByIngredientAndRecipe(
        ingredientService.getIngredientById(ingredientId),
        recipeService.getRecipeById(recipeId)
    );
  }
}

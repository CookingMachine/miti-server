package com.miti.server.service;

import com.miti.server.model.enums.Measure;
import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.repository.ContextIngredientRepository;
import com.miti.server.api.ContextIngredientService;
import com.miti.server.api.IngredientService;
import com.miti.server.api.RecipeService;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
  public ContextIngredient getContextIngredientById(Long contextIngredientId) {
    return contextIngredientRepository.findById(contextIngredientId).orElseThrow(()
        -> new RuntimeException(
        "ContextIngredient with id: " + contextIngredientId + " doesn't exist!"));
  }

  @Override
  public List<ContextIngredient> getAllContextIngredients() {
    return (List<ContextIngredient>) contextIngredientRepository.findAll();
  }

  @Override
  public List<ContextIngredient> getContextIngredientsByAmountLessThan(Long amount) {
    if (Check.param(amount)) {
      List<ContextIngredient> contextIngredients = contextIngredientRepository.
          getContextIngredientByAmountLessThan(amount);
      if (contextIngredients != null) {
        return contextIngredients;
      }
      throw new RuntimeException("Nothing is less than: " + amount);
    }
    throw new RuntimeException("Amount: " + amount + " is incorrect!");
  }

  @Override
  public List<ContextIngredient> getContextIngredientsByAmountGreaterThan(Long amount) {
    if (Check.param(amount)) {
      List<ContextIngredient> contextIngredients = contextIngredientRepository.
          getContextIngredientByAmountGreaterThan(amount);
      if (contextIngredients != null) {
        return contextIngredients;
      }
      throw new RuntimeException("Nothing is greater than: " + amount);
    }
    throw new RuntimeException("Amount: " + amount + " is incorrect!");

  }

  @Override
  public List<ContextIngredient> getContextIngredientsByMeasure(String measureName) {
    if (Check.param(measureName)) {
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
    if (Check.param(ingredientId)) {
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
  public List<ContextIngredient> getContextIngredientsByRecipeId(Long recipeId) {
    if (Check.param(recipeId)) {
      List<ContextIngredient> contextIngredients = contextIngredientRepository.
          getContextIngredientsByRecipe(recipeService.getRecipeById(recipeId));
      if (contextIngredients != null) {
        return contextIngredients;
      }
      throw new RuntimeException("ContextIngredient with recipeId: " + recipeId + " don't exist!");
    }
    throw new RuntimeException("RecipeId: " + recipeId + " is incorrect!");
  }

  @Override
  public void deleteContextIngredientById(Long contextIngredientId) {
    contextIngredientRepository.deleteById(contextIngredientId);
  }

  private boolean existsByIngredientIdAndRecipeId(String ingredientId, Long recipeId) {
    return !contextIngredientRepository.existsByIngredientAndRecipe(
        ingredientService.getIngredientById(ingredientId),
        recipeService.getRecipeById(recipeId)
    );
  }
}
package com.miti.server.service.impl;

import com.miti.server.enums.Measure;
import com.miti.server.model.dto.IngredientContextDTO;
import com.miti.server.model.entity.IngredientContext;
import com.miti.server.repository.IngredientContextRepository;
import com.miti.server.service.IngredientContextService;
import com.miti.server.service.IngredientService;
import com.miti.server.service.RecipeService;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IngredientContextServiceImpl implements IngredientContextService {
  private final IngredientContextRepository ingredientContextRepository;
  private final RecipeService recipeService;
  private final IngredientService ingredientService;

  @Override
  public IngredientContext addIngredientContext(IngredientContextDTO ingredientContext) {
    if (checkFields(
        ingredientContext.getRecipeId(),
        ingredientContext.getIngredientId()
    ))
      return ingredientContextRepository.save(new IngredientContext(
          ingredientContext.getAmount(),
          ingredientContext.getMeasure(),
          ingredientService.getIngredientById(ingredientContext.getIngredientId()),
          recipeService.getRecipeById(ingredientContext.getRecipeId())
      ));
    throw new RuntimeException("In recipe with id: " + ingredientContext.getRecipeId() + " " +
        "already exist ingredient with id: " + ingredientContext.getIngredientId());
  }

  @Override
  public void addAllIngredientContexts(List<IngredientContext> ingredientContexts) {
    List<IngredientContext> _ingredientContexts = new ArrayList<>();
    for (IngredientContext ingredientContext : ingredientContexts) {
      if (checkFields(
          ingredientContext.getRecipeIngredients().getId(),
          ingredientContext.getIngredient().getId()
      ))
        _ingredientContexts.add(ingredientContext);
    }
    ingredientContextRepository.saveAll(_ingredientContexts);
  }

  @Override
  public IngredientContext getIngredientContextById(Long ingredientContextId) {
    return ingredientContextRepository.findById(ingredientContextId).orElseThrow(()
        ->new RuntimeException("IngredientContext with id: " + ingredientContextId + " doesn't exist!"));
  }

  @Override
  public List<IngredientContext> getAllIngredientContexts() {
    return ingredientContextRepository.findAll();
  }

  @Override
  public List<IngredientContext> getIngredientContextsByAmountLessThan(Long amount) {
    if (Check.param(amount)) {
      List<IngredientContext> ingredientContexts = ingredientContextRepository.
          getIngredientContextsByAmountLessThan(amount);
      if (ingredientContexts != null)
        return ingredientContexts;
      throw new RuntimeException("Nothing is less than: " + amount);
    }
    throw new RuntimeException("Amount: " + amount + " is incorrect!");
  }

  @Override
  public List<IngredientContext> getIngredientContextByAmountGreaterThan(Long amount) {
    if (Check.param(amount)) {
      List<IngredientContext> ingredientContexts = ingredientContextRepository.
          getIngredientContextByAmountGreaterThan(amount);
      if (ingredientContexts != null)
        return ingredientContexts;
      throw new RuntimeException("Nothing is greater than: " + amount);
    }
    throw new RuntimeException("Amount: " + amount + " is incorrect!");

  }

  @Override
  public List<IngredientContext> getIngredientContextsByMeasure(String measureName) {
    if (Check.param(measureName)) {
      Measure measure = Measure.valueOf(measureName);
      List<IngredientContext> ingredientContexts = ingredientContextRepository.
          getIngredientContextsByMeasure(measure);
      if (ingredientContexts != null)
        return ingredientContexts;
      throw new RuntimeException("IngredientContexts with measure: " + measureName + " don't exist!");
    }
    throw new RuntimeException("MeasureName: " + measureName + " is incorrect!");

  }

  @Override
  public List<IngredientContext> getIngredientContextsByIngredientId(String ingredientId) {
    if (Check.param(ingredientId)) {
      List<IngredientContext> ingredientContexts = ingredientContextRepository.
          getIngredientContextsByIngredient(ingredientService.getIngredientById(ingredientId));
      if (ingredientContexts != null)
        return ingredientContexts;
      throw new RuntimeException("IngredientContexts with ingredientId: " + ingredientId + " don't exist!");
    }
    throw new RuntimeException("IngredientId: " + ingredientId + " is incorrect!");
  }

  @Override
  public List<IngredientContext> getIngredientContextsByRecipeId(Long recipeId) {
    if (Check.param(recipeId)) {
      List<IngredientContext> ingredientContexts = ingredientContextRepository.
          getIngredientContextsByRecipeIngredients(recipeService.getRecipeById(recipeId));
      if (ingredientContexts != null)
        return ingredientContexts;
      throw new RuntimeException("IngredientContexts with recipeId: " + recipeId + " don't exist!");
    }
    throw new RuntimeException("RecipeId: " + recipeId + " is incorrect!");
  }

  @Override
  public void deleteIngredientContextById(Long ingredientContextId) {
    ingredientContextRepository.deleteById(ingredientContextId);
  }

  private boolean checkFields(Long recipeId, String ingredientId) {
    return !ingredientContextRepository.existsByIngredientAndRecipeIngredients(
        ingredientService.getIngredientById(ingredientId),
        recipeService.getRecipeById(recipeId)
    );
  }
}
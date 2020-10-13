package com.miti.server.service;

import com.miti.server.model.entity.IngredientContext;

import java.util.List;

public interface IngredientContextService {
  IngredientContext addIngredientContext(IngredientContext ingredientContext);
  void addAllIngredientContexts(List<IngredientContext> ingredientContexts);

  IngredientContext getIngredientContextById(Long ingredientContextId);

  List<IngredientContext> getAllIngredientContexts();
  List<IngredientContext> getIngredientContextsByAmountLessThan(Long amount);
  List<IngredientContext> getIngredientContextByAmountGreaterThan(Long amount);
  List<IngredientContext> getIngredientContextsByMeasure(String measureName);
  List<IngredientContext> getIngredientContextsByIngredientId(String ingredientId);
  List<IngredientContext> getIngredientContextsByRecipeId(Long recipeId);

  void deleteIngredientContextById(Long ingredientContextId);
}

package com.miti.server.api;

import com.miti.server.model.entity.ContextIngredient;

import java.util.List;

public interface ContextIngredientService {

  ContextIngredient addContextIngredient(ContextIngredient contextIngredient);

  void addAllContextIngredients(List<ContextIngredient> contextIngredients);

  ContextIngredient getContextIngredientById(Long contextIngredientId);

  List<ContextIngredient> getAllContextIngredients();

  List<ContextIngredient> getContextIngredientsByAmountLessThan(Long amount);

  List<ContextIngredient> getContextIngredientsByAmountGreaterThan(Long amount);

  List<ContextIngredient> getContextIngredientsByMeasure(String measureName);

  List<ContextIngredient> getContextIngredientsByIngredientId(String ingredientId);

  List<ContextIngredient> getContextIngredientsByRecipeId(Long recipeId);

  void deleteContextIngredientById(Long contextIngredientId);
}

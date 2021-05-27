package com.miti.server.service;

import com.miti.data.model.ContextIngredient;
import java.util.List;

public interface ContextIngredientService {

  ContextIngredient addContextIngredient(ContextIngredient contextIngredient);

  void addAllContextIngredients(List<ContextIngredient> contextIngredients);

  ContextIngredient getContextIngredientById(long contextIngredientId);

  List<ContextIngredient> getAllContextIngredients();

  List<ContextIngredient> getContextIngredientsByAmountLessThan(long amount);

  List<ContextIngredient> getContextIngredientsByAmountGreaterThan(long amount);

  List<ContextIngredient> getContextIngredientsByMeasure(String measureName);

  List<ContextIngredient> getContextIngredientsByIngredientId(String ingredientId);

  List<ContextIngredient> getContextIngredientsByRecipeId(long recipeId);

  void deleteContextIngredientById(long contextIngredientId);
}

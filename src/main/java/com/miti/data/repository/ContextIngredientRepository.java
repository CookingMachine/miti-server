package com.miti.data.repository;

import com.miti.data.model.Ingredient;
import com.miti.data.model.Recipe;
import com.miti.data.enums.Measure;
import com.miti.data.model.ContextIngredient;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextIngredientRepository extends CrudRepository<ContextIngredient, Long> {

  List<ContextIngredient> getContextIngredientByAmountLessThan(Long amount);

  List<ContextIngredient> getContextIngredientByAmountGreaterThan(Long amount);

  List<ContextIngredient> getContextIngredientsByMeasure(Measure measure);

  List<ContextIngredient> getContextIngredientsByIngredient(Ingredient ingredient);

  List<ContextIngredient> getContextIngredientsByRecipe(Recipe recipe);

  boolean existsByIngredientAndRecipe(Ingredient ingredient, Recipe recipe);
}

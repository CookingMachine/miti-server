package com.miti.server.repository;

import com.miti.server.enums.Measure;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContextIngredientRepository extends JpaRepository<ContextIngredient, Long> {
  List<ContextIngredient> getContextIngredientByAmountLessThan(Long amount);
  List<ContextIngredient> getContextIngredientByAmountGreaterThan(Long amount);
  List<ContextIngredient> getContextIngredientsByMeasure(Measure measure);
  List<ContextIngredient> getContextIngredientsByIngredient(Ingredient ingredient);
  List<ContextIngredient> getContextIngredientsByRecipe(Recipe recipe);

  boolean existsByIngredientAndRecipe(Ingredient ingredient, Recipe recipe);
}

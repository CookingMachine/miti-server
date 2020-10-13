package com.miti.server.repository;

import com.miti.server.enums.Measure;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.IngredientContext;
import com.miti.server.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientContextRepository extends JpaRepository<IngredientContext, Long> {
  List<IngredientContext> getIngredientContextsByAmountLessThan(Long amount);
  List<IngredientContext> getIngredientContextByAmountGreaterThan(Long amount);
  List<IngredientContext> getIngredientContextsByMeasure(Measure measure);
  List<IngredientContext> getIngredientContextsByIngredient(Ingredient ingredient);
  List<IngredientContext> getIngredientContextsByRecipe(Recipe recipe);

  boolean existsByIngredientAndRecipe(Ingredient ingredient, Recipe recipe);
}

package com.miti.server.repository;

import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.enums.Measure;
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

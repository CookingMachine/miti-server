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
    List<IngredientContext> getIngredientContextsByAmountLessThan(Integer amount);
    List<IngredientContext> getIngredientContextByAmountGreaterThan(Integer amount);
    List<IngredientContext> getIngredientContextsByMeasure(Measure measure);
    List<IngredientContext> getIngredientContextsByIngredient(Ingredient ingredient);
    List<IngredientContext> getIngredientContextsByRecipeIngredients(Recipe recipe);

    boolean existsByIngredientAndRecipeIngredients(Ingredient ingredient, Recipe recipe);
}

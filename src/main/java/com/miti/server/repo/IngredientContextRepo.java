package com.miti.server.repo;

import com.miti.server.entity.IngredientContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientContextRepo extends JpaRepository<IngredientContext, Long> {
    IngredientContext getIngredientContextByRecipeIngredients(Long recipeId);
}

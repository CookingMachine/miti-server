package com.miti.server.repository;

import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.enums.IngredientCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {

  Ingredient getIngredientByName(String name);

  List<Ingredient> getIngredientsByCategory(IngredientCategory category);

  boolean existsByName(String name);
}

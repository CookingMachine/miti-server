package com.miti.data.repository;

import com.miti.data.model.Ingredient;
import com.miti.data.enums.IngredientCategory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {

  Ingredient getIngredientByName(String name);

  List<Ingredient> getIngredientsByCategory(IngredientCategory category);

  boolean existsByName(String name);
}

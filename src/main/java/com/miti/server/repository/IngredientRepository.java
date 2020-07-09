package com.miti.server.repository;

import com.miti.server.enums.IngredientCategory;
import com.miti.server.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    Ingredient findIngredientByName(String name);

    List<Ingredient> getIngredientsByCategory(IngredientCategory category);
}

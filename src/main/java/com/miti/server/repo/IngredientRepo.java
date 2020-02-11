package com.miti.server.repo;

import com.miti.server.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
    Ingredient findIngredientByName(String name);
}

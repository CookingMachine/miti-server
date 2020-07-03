package com.miti.server.repository;

import com.miti.server.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findIngredientByName(String name);
}

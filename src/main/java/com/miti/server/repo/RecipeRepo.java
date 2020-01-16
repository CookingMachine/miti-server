package com.miti.server.repo;

import com.miti.server.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {
}

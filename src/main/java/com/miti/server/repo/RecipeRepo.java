package com.miti.server.repo;

import com.miti.server.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {
    List<Recipe> getRecipesByName(String name);

    List<Recipe> getRecipesByAuthor(String author);
}

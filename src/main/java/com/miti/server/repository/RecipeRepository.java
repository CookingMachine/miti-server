package com.miti.server.repository;

import com.miti.server.entity.Category;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> getRecipesByAuthor(User user);

    List<Recipe> getRecipesByCategory(Category category);

    List<Recipe> getRecipesByCategoryId(Long categoryId);
}

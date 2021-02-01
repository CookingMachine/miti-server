package com.miti.server.repository;

import com.miti.server.enums.Kitchen;
import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

  Recipe getRecipeByName(String name);

  List<Recipe> getRecipesByAuthor(User author);

  List<Recipe> getRecipesByCategory(Category category);

  List<Recipe> getRecipesByKitchen(Kitchen kitchen);

  List<Recipe> getRecipesByTimeLessThanEqual(int time);

  boolean existsByName(String name);
}

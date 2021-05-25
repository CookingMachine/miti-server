package com.miti.server.repository;

import com.miti.server.model.entity.CalorieContent;
import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import com.miti.server.model.enums.Kitchen;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

  Recipe getRecipeByCalorie(CalorieContent calorieContent);

  List<Recipe> getRecipesByName(String name);

  List<Recipe> getRecipesByAuthor(User author);

  List<Recipe> getRecipesByCategory(Category category);

  List<Recipe> getRecipesByKitchen(Kitchen kitchen);

  List<Recipe> getRecipesByTimeLessThanEqual(int time);

  List<Recipe> getRecipesByCreateDateBetween(LocalDateTime recipePublicationDateStart, LocalDateTime today);

  List<Recipe> getRecipesByFavouriteUsers(User user);
}

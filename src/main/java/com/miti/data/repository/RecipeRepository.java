package com.miti.data.repository;

import com.miti.data.model.Recipe;
import com.miti.data.model.User;
import com.miti.data.enums.Kitchen;
import com.miti.data.model.CalorieContent;
import com.miti.data.model.Category;
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

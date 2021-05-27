package com.miti.data.repository;

import com.miti.data.model.Recipe;
import com.miti.data.model.User;
import com.miti.data.model.Rating;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

  Rating getRatingByRecipeAndUser(Recipe recipe, User user);

  List<Rating> getRatingsByRecipeId(Long recipeId);
}

package com.miti.server.repository;

import com.miti.server.model.entity.Rating;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

  Rating getRatingByRecipeAndUser(Recipe recipe, User user);

  List<Rating> getRatingsByRecipeId(Long recipeId);
}

package com.miti.server.service;

import com.miti.server.model.entity.Rating;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import java.util.List;

public interface RatingService {

  Rating addRating(Rating rating);

  void addAllRatings(List<Rating> ratings);

  Rating editRating(Long ratingId, Rating rating);

  Rating getRatingByRecipeAndUser(Recipe recipe, User user);

  List<Rating> getRatingsByRecipeId(Long recipeId);
}

package com.miti.server.service;

import com.miti.data.model.Rating;
import com.miti.data.model.Recipe;
import com.miti.data.model.User;
import java.util.List;

public interface RatingService {

  Rating addRating(Rating rating);

  void addAllRatings(List<Rating> ratings);

  Rating editRating(Rating rating);

  Rating getRatingByRecipeAndUser(Recipe recipe, User user);

  List<Rating> getRatingsByRecipeId(Long recipeId);
}

package com.miti.server.service.impl;

import com.miti.data.model.Rating;
import com.miti.data.model.Recipe;
import com.miti.data.model.User;
import com.miti.data.repository.RatingRepository;
import com.miti.server.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

  private final RatingRepository ratingRepository;

  @Override
  public Rating addRating(Rating rating) {
    if (ratingRepository.getRatingByRecipeAndUser(rating.getRecipe(), rating.getUser()) == null) {
      return ratingRepository.save(rating);
    }
    return editRating(rating);
  }

  @Override
  public void addAllRatings(List<Rating> ratings) {
    ratingRepository.saveAll(ratings);
  }

  @Override
  public Rating editRating(Rating newRating) {
    return ratingRepository.findById(newRating.getId()).map(rating -> {
      rating.setRatingValue(newRating.getRatingValue());
      return rating;
    }).orElseThrow(()
        -> new RuntimeException("Rating with id: " + newRating.getId() + " doesn't exist!"));
  }

  @Override
  public Rating getRatingByRecipeAndUser(Recipe recipe, User user) {
    return ratingRepository.getRatingByRecipeAndUser(recipe, user);
  }

  @Override
  public List<Rating> getRatingsByRecipeId(Long recipeId) {
    return ratingRepository.getRatingsByRecipeId(recipeId);
  }
}

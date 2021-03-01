package com.miti.server.service.impl;

import com.miti.server.model.entity.Rating;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import com.miti.server.repository.RatingRepository;
import com.miti.server.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RatingServiceImpl implements RatingService {

  private final RatingRepository ratingRepository;

  @Override
  public Rating addRating(Rating rating) {
    if (ratingRepository.getRatingByRecipeAndUser(rating.getRecipe(), rating.getUser()) == null) {
      return ratingRepository.save(rating);
    }
    return editRating(
        ratingRepository.getRatingByRecipeAndUser(rating.getRecipe(), rating.getUser()).getId(),
        rating);
  }

  @Override
  public void addAllRatings(List<Rating> ratings) {
    ratingRepository.saveAll(ratings);
  }

  @Override
  public Rating editRating(Long ratingId, Rating newRating) {
    return ratingRepository.findById(ratingId).map(rating -> {
      rating.setRatingValue(newRating.getRatingValue());
      return rating;
    }).orElseThrow(() -> new RuntimeException("Rating with id: " + ratingId + " doesn't exist!"));
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

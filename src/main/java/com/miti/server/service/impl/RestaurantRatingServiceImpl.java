package com.miti.server.service.impl;

import com.miti.data.model.RestaurantRating;
import com.miti.data.repository.RestaurantRatingRepository;
import com.miti.server.service.RestaurantRatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantRatingServiceImpl implements RestaurantRatingService {

  private final RestaurantRatingRepository restaurantRatingRepository;

  @Override
  public void addRestaurantRating(RestaurantRating rating) {
    restaurantRatingRepository.save(rating);
  }
}

package com.miti.server.service.impl;

import com.miti.data.model.Restaurant;
import com.miti.data.repository.RestaurantRepository;
import com.miti.server.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

  private final RestaurantRepository restaurantRepository;

  @Override
  public Restaurant addRestaurant(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);
  }

  @Override
  public void addAllRestaurants(List<Restaurant> restaurantList) {
    restaurantRepository.saveAll(restaurantList);
  }

  @Override
  public List<Restaurant> getAllRestaurants() {
    return restaurantRepository.findAll();
  }
}

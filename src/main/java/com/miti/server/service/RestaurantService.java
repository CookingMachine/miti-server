package com.miti.server.service;

import com.miti.data.model.Restaurant;

import java.util.List;

public interface RestaurantService {

  Restaurant addRestaurant(Restaurant restaurant);

  void addAllRestaurants(List<Restaurant> restaurantList);

  List<Restaurant> getAllRestaurants();

}

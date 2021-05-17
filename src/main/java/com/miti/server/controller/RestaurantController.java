package com.miti.server.controller;

import com.miti.data.model.Restaurant;
import com.miti.server.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/restaurant")
@AllArgsConstructor
public class RestaurantController {

  private final RestaurantService restaurantService;

  @PostMapping("")
  public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
    return restaurantService.addRestaurant(restaurant);
  }

  @GetMapping("")
  public List<Restaurant> getAllRestaurants(){
    return restaurantService.getAllRestaurants();
  }
}

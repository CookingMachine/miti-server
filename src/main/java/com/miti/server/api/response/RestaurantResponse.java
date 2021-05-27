package com.miti.server.api.response;

import com.miti.data.enums.Tag;
import com.miti.data.model.Recipe;
import com.miti.data.model.RestaurantRating;

import java.util.List;
import java.util.UUID;

public class RestaurantResponse {

  private UUID id;

  private String title;

  private String kitchen;

  private String destination;

  private String metroStation;

  private String averageBill;

  private List<Tag> tags;

  private List<RestaurantRating> rating;

  private List<Recipe> recipes;
}

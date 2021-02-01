package com.miti.server.service;

import com.miti.server.model.entity.Rating;

import java.util.List;

public interface RatingService {

  void addAllRatings(List<Rating> ratings);
}

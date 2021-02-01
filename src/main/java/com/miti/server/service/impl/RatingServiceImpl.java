package com.miti.server.service.impl;

import com.miti.server.model.entity.Rating;
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
  public void addAllRatings(List<Rating> ratings) {
    ratingRepository.saveAll(ratings);
  }
}
